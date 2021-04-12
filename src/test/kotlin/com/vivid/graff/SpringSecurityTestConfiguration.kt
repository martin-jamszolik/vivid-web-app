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

import com.vivid.graff.security.Role
import com.vivid.graff.security.User
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsPasswordService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.UserDetailsManager

@TestConfiguration
class SpringSecurityTestConfiguration {

    @Primary
    @Bean
    fun userDetailService(): UserDetailsService {
        val usr1 = User.Builder(1, "first_user", "test", roles = arrayListOf(Role(id = 1L, roleName = "ADMIN")))
            .fullName("First User")
            .client("first")
            .build()

        val usr2 = User.Builder(2, "second_user", "test", roles = arrayListOf(Role(id = 1L, roleName = "ADMIN")))
            .fullName("Second User")
            .client("second")
            .build()

        return VividInMemoryUserDetailsManager(usr1,usr2);
    }

}



class VividInMemoryUserDetailsManager : UserDetailsManager, UserDetailsPasswordService {
    private val users = HashMap<String, UserDetails>()

    constructor(vararg s: UserDetails){
        for (it in s) {
            createUser(it)
        }
    }


    override fun loadUserByUsername(username: String): UserDetails? {
       return users[username]
    }

    override fun createUser(user: UserDetails) {
        users[user.username] = user
    }

    override fun updateUser(user: UserDetails) {
        users[user.username] = user
    }

    override fun deleteUser(username: String) {
        users.remove(username)
    }

    override fun changePassword(oldPassword: String, newPassword: String) {
        TODO("Not yet implemented")
    }

    override fun userExists(username: String): Boolean {
        return users.containsKey(username)
    }

    override fun updatePassword(user: UserDetails, newPassword: String): UserDetails {
        return user
    }

}