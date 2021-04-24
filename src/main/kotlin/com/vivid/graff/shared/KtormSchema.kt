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

import org.ktorm.schema.*

object Locations : Table<Location>("location") {
    val id = int("el_key").primaryKey().bindTo { it.id }
    val address = varchar("address").bindTo { it.address }
    val city = varchar("city").bindTo { it.city }
    val state = varchar("state").bindTo { it.state }
    val zip = varchar("zip").bindTo { it.zip }
}

object Projects : Table<Project>("project") {
    val id = int("pp_key").primaryKey().bindTo { it.id }
    val locationId = int("el_key").references(Locations) {it.location}
    val estimatorId = int("e_key").references(Estimators) {it.estimator}
    val companyId = int("c_key").references(Companies) {it.company}
    val name = varchar("pp_name").bindTo {it.name}
    val tax = decimal("tax").bindTo { it.tax }
    val status = varchar("pp_status").bindTo {it.status}
    val date = date("pp_date").bindTo {it.date}
    val owner =varchar( "pp_owner" ).bindTo {it.owner}
}

object Companies: Table<Company> ( "company"){
    val id = int("c_key").primaryKey().bindTo { it.id }
    val name = varchar("c_name").bindTo { it.name }
    val description = varchar ( "c_desc").bindTo { it.description }
    val address1 = varchar( "address1").bindTo { it.address1 }
    val address2 = varchar("address2").bindTo { it.address2 }
    val contact =  varchar("contact").bindTo { it.contact }
    val visible = boolean("visible" ).bindTo { it.visible }
    val isDefault = boolean("is_default").bindTo { it.isDefault }
}

object Estimators: Table<Estimator>("estimator"){
    val id = int("e_key").primaryKey().bindTo { it.id }
    val userId = int( "us_key").bindTo { it.userId }
    val name = varchar("e_name").bindTo { it.name }
    val isAdmin = boolean("e_admin").bindTo { it.isAdmin }
    val isVisible = boolean("visible").bindTo { it.isVisible }
}

object Proposals: Table<Proposal>("proposal"){
    val id = int("pr_key").primaryKey().bindTo { it.id }
    val projectId = int("pp_key").bindTo{ it.projectId }
    val date = date("pr_date").bindTo { it.date }
    val name = varchar("pr_name").bindTo { it.title }
    val editable = boolean("editable").bindTo { it.editable }
    val profit = decimal("p_pr").bindTo { it.profit }
    val profitType= int("p_pr_type").bindTo { it.profitType }
    val status =varchar ("pr_status").bindTo { it.status }
    val authorized = date("authorize").bindTo { it.authorized }
}

object Tasks: Table<Task>("task"){
    val id = int("pst_key").primaryKey().bindTo { it.id }
    val proposalId = int("pr_key").bindTo { it.proposalId}
    val detail = varchar("pst_descr").bindTo { it.detail }
    val cost = decimal("c_cost").bindTo { it.cost }
    val taskIdentifier = varchar("pst_id").bindTo { it.taskIdentifier }
    val scope = varchar("s_name").bindTo { it.scope }
    val name = varchar("t_name").bindTo { it.name }
    val qty = decimal("c_qty").bindTo { it.qty }
    val unit = varchar("c_unit").bindTo { it.unit }
    val profit = decimal("t_pr").bindTo { it.profit }
    val profitType = int("t_pr_type").bindTo { it.profitType }
    val taskType = varchar("t_type").bindTo { it.taskType }
}
