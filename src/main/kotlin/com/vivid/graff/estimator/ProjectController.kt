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

import com.vivid.graff.ProjectDTO
import com.vivid.graff.shared.ProjectRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/projects")
class ProjectController(private val repo: ProjectRepository) {
    companion object {
        val OPERATORS = mapOf(":" to "=")
    }

    @GetMapping
    fun search(@RequestParam("search") search: String): List<ProjectDTO> {

        val query = HashMap<String,Pair<String,String>>()
        val regex = Regex("(\\w+?)(:)((\\w|\\s)+?),")
        regex.findAll("$search,").forEach { result: MatchResult ->
            val (criteria, operator, value) = result.destructured
            when (criteria) {
                "project" -> query[criteria]=Pair("pp_name like ?","%$value%")
                "estimator" -> query[criteria]=Pair("e_key ${OPERATORS.getOrDefault(operator,"=")} ? ", value)
            }
        }

        return repo.projectsByName( query["project"]!!.second )
    }


}