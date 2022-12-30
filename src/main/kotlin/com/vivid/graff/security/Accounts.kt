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

package com.vivid.graff.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


data class Role(val id: Long,val roleName: String) : GrantedAuthority {
    override fun getAuthority(): String {
        return roleName
    }
}

data class UserRequest(var username: String = "", var password: String = "", var client: String = "" ) {
    override fun toString(): String {
        return "$client-$username"
    }
}

data class JwtSettings(val key:String, val header:String,val cookie:String,val prefix:String, val expire:Long)

data class User(val id:Long,
                private val username:String,
                private val password:String,
                val fullName:String = "",
                val client:String = "",
                var roles:Collection<GrantedAuthority>
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
}

    override fun isAccountNonExpired(): Boolean {
       return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    data class Builder(val id:Long,
                       val username:String,
                       val password:String,
                       var fullName:String="",
                       var client:String="",
                       var roles:Collection<GrantedAuthority>?= emptyList()){
        fun fullName(name:String) = apply {this.fullName=name}
        fun client(client:String) = apply {this.client=client}
        fun roles(roles: Collection<GrantedAuthority>) = apply {this.roles = roles }
        fun build() = User(id,username,password,fullName,client,roles= roles ?: ArrayList())
    }
}