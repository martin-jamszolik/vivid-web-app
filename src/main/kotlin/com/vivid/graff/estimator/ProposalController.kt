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

package com.vivid.graff.estimator

import com.vivid.graff.Proposal
import com.vivid.graff.Scope
import com.vivid.graff.Task
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/projects/{projectId}/proposals")
class ProposalController(private val repo: JdbcTemplate) {

    @GetMapping
    fun getProposals(
        @PathVariable("projectId") projectId: Long,
        @RequestParam(name = "search", required = false, defaultValue = "") search: String
    ): List<Proposal> {
        return repo.query(
            """
             SELECT pr_key as id, 
                    pp_key as projectId, 
                    pr_date as `date`,  
                    pr_name as title, 
                    p_pr_type as `type`,
                    pr_status as status,
                    authorize as authorized,
                    editable
            FROM `proposal`  WHERE pp_key = ?
        """.trimIndent(), BeanPropertyRowMapper<Proposal>(Proposal::class.java), projectId
        )
    }

    @GetMapping("/{proposalId}")
    fun getProposal(@PathVariable("proposalId") proposalId: Long): Proposal {
        val proposal = doProposal(proposalId)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Proposal Not Found"
            )


        val scopes = doScopes(proposal)

        proposal.scopes.addAll(scopes)
        return proposal
    }


    private fun doProposal(id: Long): Proposal? {
        return repo.queryForObject(
            """
             SELECT pr_key as id, 
                    pp_key as projectId, 
                    pr_date as `date`,  
                    pr_name as title, 
                    p_pr_type as `type`,
                    pr_status as status,
                    authorize as authorized,
                    editable
            FROM `proposal` WHERE pr_key = ?
        """.trimIndent(), BeanPropertyRowMapper(Proposal::class.java), id
        )
    }

    private fun doScopes(proposal: Proposal): List<Scope> {
        val scopeExtractor = ResultSetExtractor<Collection<Scope>> {
            val map = LinkedHashMap<String, Scope>()
            val taskRowMapper = BeanPropertyRowMapper<Task>(Task::class.java)
            var row = -1
            while (it.next()) {
                val scopeName = it.getString("s_name")
                val scope = map.getOrPut(scopeName) { Scope(scopeName) }
                val task = taskRowMapper.mapRow(it, ++row)
                scope.tasks.add(task)
            }

            map.values
        }

        val allScopes = repo.query(
            """
            SELECT pt.pst_key as id, 
               pst_descr as detail,
               c_cost as cost,
               pst_id as userId,
               s_name, 
               t_name as taskName,
               c_qty as qty,
               c_unit as unit,
               t_pr, 
               t_pr_type, 
               t_type as taskType
            FROM `task` pt
            WHERE pr_key = ?
            ORDER BY pt.s_name, pt.pst_key
        """.trimIndent(), scopeExtractor, proposal.id
        )

        return allScopes?.toList() ?: emptyList()

    }


}