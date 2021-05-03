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


import com.vivid.graff.md5
import org.ktorm.entity.Entity
import java.math.BigDecimal
import java.time.LocalDate

interface Location : Entity<Location> {
    companion object : Entity.Factory<Location>()
    val id: Int
    var address: String
    var city: String
    var state: String
    var zip: String
}

interface Company: Entity<Company>{
    companion object : Entity.Factory<Company>()
    val id:Int
    val name: String
    val description:String
    val address1:String
    val address2:String
    val contact:String
    val visible:Boolean
    val isDefault:Boolean
}

interface Estimator: Entity<Estimator> {
    companion object : Entity.Factory<Estimator>()
    val id: Int
    val userId: Int
    val name: String
    val isAdmin: Boolean
    val isVisible: Boolean
}

interface Project : Entity<Project> {
    companion object : Entity.Factory<Project>()
    val id:Int
    var location: Location
    var estimator: Estimator
    var company: Company
    var name:String
    var tax:BigDecimal
    var status:String
    var date: LocalDate
    var owner:String
}

interface Proposal: Entity<Proposal> {
    companion object : Entity.Factory<Proposal>()
    val id:Int
    val projectId: Int
    val date: LocalDate
    val title: String
    val editable: Boolean
    val profit: BigDecimal
    val profitType: Int
    val status : String
    val authorized: LocalDate
    var scopes: MutableList<Scope>
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

data class ProjectDTO(
    var id: Int?,
    var title: String = "",
    var address: String = "",
    var company: String = "",
    var estimator: String = "",
    var client: String? = "",
    var status: String = "",
    var date: LocalDate? = null
)

data class Page<T>(
    val offset: Int,
    val limit: Int,
    val totalRecords: Int,
    val pageSize: Int = limit,
    val totalPages: Int = if (totalRecords % pageSize == 0) totalRecords / pageSize else totalRecords / pageSize + 1,
    val currentList: List<T>
)

interface Task: Entity<Task>{
    companion object: Entity.Factory<Task>()
    val id: Int
    val proposalId:Int
    val scope: String
    val name: String
    val detail:String
    val cost:BigDecimal
    val qty:BigDecimal
    val unit:String
    val profit: BigDecimal
    val profitType: Int
    val taskType: String
    val taskIdentifier:String
}
