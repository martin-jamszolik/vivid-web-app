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