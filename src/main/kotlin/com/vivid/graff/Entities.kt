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

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.util.*


data class Project(
    @Id
    @Column("pp_key")
    var id: Long = 0,
    @Column("pp_name")
    var title: String = "",
    @Column("el_key")
    var locationId: Long = 0,
    @Column("e_key")
    var estimatorId: Long = 0,
    @Column("c_key")
    var companyId: Long = 0,
    @Column("c_key")
    var clientId: Long = 0,
    @Column("pp_status")
    var status: String = "",
    @Column("pp_date")
    var date: Date? = null,
    @Column("pp_owner")
    var owner: String = ""
)

data class ProjectDTO(
    var id: Long = 0,
    var title: String = "",
    var address: String = "",
    var company: String = "",
    var estimator: String = "",
    var client: String? = "",
    var status: String = "",
    var date: Date? = null
)


data class Proposal(
    var id: Long = 0,
    var projectId: Long = 0,
    var title: String = "",
    var date: Date = Date(),
    var type: String = "",
    var status: String = "",
    var authorized: Date? = null,
    var editable: Boolean = false,
    var accepted: Boolean = false,

    var scopes: MutableList<Scope> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Proposal

        if (id != other.id) return false
        if (projectId != other.projectId) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + projectId.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }
}

data class Scope(
    var scopeName: String = "",
    var id: String = scopeName.md5,
    var tasks: MutableList<Task> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scope

        if (id != other.id) return false
        if (scopeName != other.scopeName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + scopeName.hashCode()
        return result
    }
}


data class Task(
    var id: Long = 0,
    var index: Long = 0,
    var userId: String = "",
    var taskName: String = "",
    var detail: String = "",
    var qty: Float = 0f,
    var cost: Number = 0,
    var unit: String = "",
    var taskType: String = "",
    var taskPosition: Long = 0
)

data class DBUser(
    var id: Long = 0,
    var name: String = "",
    var fullName: String = "",
    var settings: String = ""
)

data class UserSession(var username: String = "", var password: String = "", var client: String = "") {
    override fun toString(): String {
        return "$client-$username"
    }
}
