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
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.update
import org.ktorm.schema.ColumnDeclaring
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ProjectRepository(db: Database) : BaseRepository(db) {

    val projects get() = db.sequenceOf(Projects)
    val companies get() = db.sequenceOf(Companies)
    val locations get() = db.sequenceOf(Locations)
    val estimators get() = db.sequenceOf(Estimators)

    @Transactional
    fun add(entity: Project): Int {
        return projects.add(entity)
    }

    @Transactional
    fun save(entity: Project): Int {
        return projects.update(entity)
    }

    fun projectsWhere(condition: ColumnDeclaring<Boolean>, limit: Pair<Int, Int>): Page<ProjectDTO> {
        val query = db
            .from(Projects)
            .innerJoin(Locations, on = Projects.locationId eq Locations.id)
            .innerJoin(Companies, on = Projects.companyId eq Companies.id)
            .innerJoin(Estimators, on = Projects.estimatorId eq Estimators.id)
            .select(
                Projects.id,
                Projects.name,
                Locations.address,
                Projects.status,
                Projects.date,
                Companies.name,
                Estimators.name
            )
            .where(condition)
            .limit(limit.first, limit.second);

        val list = query.map { row ->
            ProjectDTO(
                id = row[Projects.id],
                title = row[Projects.name].orEmpty(),
                address = row[Locations.address].orEmpty(),
                company = row[Companies.name].orEmpty(),
                estimator = row[Estimators.name].orEmpty(),
                client = "",
                status = row[Projects.status].orEmpty(),
                date = row[Projects.date]
            )
        }

        return Page(
            offset = limit.first,
            limit = limit.second,
            totalRecords = query.totalRecords,
            currentList = list
        )
    }
}