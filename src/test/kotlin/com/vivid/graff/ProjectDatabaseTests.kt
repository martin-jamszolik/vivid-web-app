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

import com.vivid.graff.shared.ProjectDatabase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.springframework.beans.factory.annotation.Autowired

class ProjectDatabaseTests: VividApplicationTests()  {
    @Autowired
    lateinit var projectDatabase: ProjectDatabase

    @Test
    fun `query for project list using Kotlin ORM`(){
        val list = projectDatabase.projectsByName ("1030 5th Avenue")
        assertThat(list).isNotEmpty
    }

    @Test
    fun `find project by Id`(){
        val project = projectDatabase.projects.find { it.id eq 1 }
        assertThat(project).isNotNull
        assertThat(project!!.id).isSameAs( 1 )

    }

}