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

import com.vivid.graff.Project
import org.springframework.http.MediaType
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/projects")
class ProjectController(private val repo: JdbcTemplate) {
    companion object {
        val OPERATORS = mapOf(":" to "=")
    }

    @GetMapping
    fun search(@RequestParam("search") search: String): List<Project> {

        val query = HashMap<String,Pair<String,String>>()
        val regex = Regex("(\\w+?)(:)((\\w|\\s)+?),")
        regex.findAll("$search,").forEach { result: MatchResult ->
            val (criteria, operator, value) = result.destructured


            when (criteria) {
                "project" -> query[criteria]=Pair("pp_name like ?","%$value%")
                "estimator" -> query[criteria]=Pair("e_key ${OPERATORS.getOrDefault(operator,"=")} ? ", value)
            }
        }

        return repo.query("""
            SELECT pp_key as id,
                   pp_name as title,
                   address as address,
                   pp_status as status, 
                   pp_date as date,
                   c_name as company,
                   e_name as estimator
              FROM `project` p 
              INNER JOIN `location` e USING (el_key)
              INNER JOIN company c  USING (c_key)
              INNER JOIN estimator e2 USING ( e_key)
              WHERE pp_name like ?
        """.trimIndent(), BeanPropertyRowMapper(Project::class.java), query["project"]?.second)
    }


}