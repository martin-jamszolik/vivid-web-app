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

import com.vivid.graff.estimator.ProjectRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(classes = [SpringSecurityTestConfiguration::class] )
class ProjectRepositoryTests {

    init {
        System.setProperty("jasypt.encryptor.password", "testonlyprofile")
    }

    @Autowired
    lateinit var projectRepository: ProjectRepository


    @Test
    @WithUserDetails("first_user")
    fun `should find one Project for first client`() {
        val list = projectRepository.findProjects("%1030 5th Avenue%")
        assertThat(list).isNotEmpty
    }

    @Test
    @WithUserDetails("second_user")
    fun `should find one Project for second client`() {
        val list = projectRepository.findProjects("%80 8th Avenue%")
        assertThat(list).isNotEmpty
    }


}