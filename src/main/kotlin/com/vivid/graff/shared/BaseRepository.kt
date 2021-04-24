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
import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.schema.Table
import org.springframework.stereotype.Repository

@Repository
abstract class BaseRepository(val db: Database) {

    fun from(table: Table<*>): QuerySource {
        return db.from(table)
    }

    fun getPrimaryKey(table: Table<*>, columnId: Column<Int>, whereCondition: ColumnDeclaring<Boolean>): Int? {
        return db.from(table)
            .select(columnId)
            .where(whereCondition)
            .map { it[columnId] }.first()
    }

    fun <T : BaseTable<*>> insert(table: T, block: AssignmentsBuilder.(T) -> Unit): Int {
        return db.insertAndGenerateKey(table, block) as Int
    }
}