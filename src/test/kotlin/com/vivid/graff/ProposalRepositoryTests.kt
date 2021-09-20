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

import com.vivid.graff.shared.ProposalRepository
import com.vivid.graff.shared.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProposalRepositoryTests: VividApplicationTests() {
    @Autowired
    lateinit var proposalRepository: ProposalRepository

    @Test
    fun `should load all proposal for a project`() {
        val list = proposalRepository.proposalsByProject(1)
        assertThat(list).isNotEmpty
    }

    @Test
    fun `should get a Proposal with all tasks loaded`(){
        val proposal = proposalRepository.proposalById(1)
        assertThat(proposal).isNotNull
        assertThat(proposal.scopes).isNotEmpty
    }

    @Test
    fun `should insert a new Task in a Proposal`(){
        val proposal = proposalRepository.proposalById(1)
        val task = Task {
            proposalId = proposal.id
            scope = "Test"
            name = "UnitTest Task"
            detail = "With more Details"
            cost= 123.0.toBigDecimal()
            qty = 10.0.toBigDecimal()
            unit = "unit"
            profit = 0.0.toBigDecimal()
            profitType = 0
            taskType= "Default"
            taskIdentifier = "1)"
        }

        proposalRepository.insertTask(task)
        assertThat(task.id).isNotNull

    }
}