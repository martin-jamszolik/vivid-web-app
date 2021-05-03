/*
 * Copyright (c) 2021 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.vivid.graff

import com.vivid.graff.shared.*
import com.vivid.graff.shared.Location
import com.vivid.graff.shared.Project
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.ktorm.dsl.eq
import org.ktorm.dsl.like
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.first
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class ProjectRepositoryTests : VividApplicationTests() {
    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Test
    fun `query for project list using Kotlin ORM`() {
        val list = projectRepository.projectsWhere(Projects.name like "%1030 5th Ave%")
        assertThat(list).isNotEmpty
    }

    @Test
    fun `find project by Id`() {
        val project = projectRepository.projects.find { it.id eq 1 }
        assertThat(project).isNotNull
        assertThat(project!!.id).isSameAs(1)

    }


    @Test
    fun `should save a new Project using existing references`() {

        val locationId = projectRepository.getPrimaryKey(
            Locations,
            Locations.id, (Locations.address eq "123 Main Ave")
        )
        assertThat(locationId).isEqualTo(1)

        val companyId = projectRepository.getPrimaryKey(Companies, Companies.id, (Companies.name eq "First LLC"))
        assertThat(companyId).isEqualTo(1)

        val estimatorId = projectRepository.getPrimaryKey(Estimators, Estimators.id, (Estimators.name eq "Administrator"))

        val projectId = projectRepository.insert(Projects) {
            set(it.companyId, companyId)
            set(it.locationId, locationId)
            set(it.estimatorId, estimatorId)
            set(it.date, LocalDate.now())
            set(it.name, "My Favorite Project")
            set(it.status, "Draft")
        }

        val projectEntity = projectRepository.projects.find { it.id eq projectId }

        assertThat(projectEntity).isNotNull
        assertThat("My Favorite Project").isEqualTo(projectEntity!!.name)

    }

    @Test
    fun `should add new Entity`() {
        val loc = Location { address = "1234 Main";city = "Test";state = "FL";zip = "1234" }
        projectRepository.locations.add(loc)
        assertThat(loc.id).isNotNull

        val anotherOne = Project {
            name = "Jerry Project"
            location = loc
            company = projectRepository.companies.first { it.id eq 1 }
            estimator = projectRepository.estimators.first { it.id eq 1 }
            date = LocalDate.now()
            status = "Draft"
        }
        val inserted = projectRepository.projects.add(anotherOne)
        assertThat(inserted).isEqualTo(1)
        assertThat(anotherOne.id).isNotNull
    }

}