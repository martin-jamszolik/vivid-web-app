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


import com.vivid.graff.shared.Page
import com.vivid.graff.shared.ProjectDTO
import com.vivid.graff.shared.ProjectRepository
import com.vivid.graff.shared.Projects
import org.ktorm.dsl.isNotNull
import org.ktorm.dsl.like
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/projects")
class ProjectController(private val repo: ProjectRepository) {

    @GetMapping
    fun search(
        @RequestParam("search") search: String,
        @RequestParam("offset") offset: Int,
        @RequestParam("limit") limit: Int
    ): Page<ProjectDTO> {

        return if (search.isNotBlank())
            repo.projectsWhere(Projects.name like "%$search%", Pair(offset, limit))
        else
            repo.projectsWhere(Projects.name.isNotNull(), Pair(offset, limit));

    }


}