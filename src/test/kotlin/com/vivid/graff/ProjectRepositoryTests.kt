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

import com.vivid.graff.shared.ProjectRepository
import com.vivid.graff.shared.LocationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithUserDetails
import java.util.*

class ProjectRepositoryTests: VividApplicationTests() {

    @Autowired
    lateinit var projectRepo: ProjectRepository

    @Autowired
    lateinit var locationRepo: LocationRepository


    @Test
    fun `should find one Project for first client`() {
        val list = projectRepo.findProjects("%1030 5th Avenue%")
        assertThat(list).isNotEmpty
    }

    @Test
    @WithUserDetails("second_user")
    fun `should find one Project for second client`() {
        val list = projectRepo.findProjects("%80 8th Avenue%")
        assertThat(list).isNotEmpty
    }

    @Test
    fun `save new project model`(){
        val project = Project(null,"Testing",
            locationRepo.save(
                Location(null,"123 Main Street","Sarasota","FL","34219")
            ).id,
            1,
            1,
            1,
            "Draft",
            Date(),
            "owner")

        val saved = projectRepo.save(project)
        assertThat(saved).isNotNull
        assertThat(saved.id).isNotNegative
        assertThat( saved.locationId ).isNotNull
        assertThat( saved.locationId ).isSameAs(
            locationRepo.findById(saved.locationId!!).get().id)

    }


}