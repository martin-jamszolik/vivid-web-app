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

package com.vivid.graff.shared

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.*
import org.springframework.stereotype.Repository

@Repository
class ProposalRepository(db: Database) : BaseRepository(db) {

    val proposals get() = db.sequenceOf(Proposals)
    val tasks get() = db.sequenceOf(Tasks)

    fun proposalsByProject(projectId: Int): List<Proposal> {
        return proposals
            .filter { it.projectId eq projectId }
            .toList()
    }

    fun proposalById(proposalId: Int): Proposal {
        val map = LinkedHashMap<String, Scope>()
        val proposal = proposals.find { it.id eq proposalId }!!

        tasks.filter { it.proposalId eq proposalId }
            .map { t -> map.getOrPut(t.scope) { Scope(t.scope) }.tasks.add(t) }
        proposal.scopes = map.values.toMutableList()
        return proposal
    }


}