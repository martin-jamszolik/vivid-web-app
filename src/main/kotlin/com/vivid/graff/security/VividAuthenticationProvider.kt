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

import com.vivid.graff.MultiDataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

class VividAuthenticationProvider(
    private val dsService: MultiDataSource,
    private val encoder: PasswordEncoder
) : AbstractUserDetailsAuthenticationProvider() {


    override fun retrieveUser(username: String?,
                              authentication: UsernamePasswordAuthenticationToken): UserDetails {
        val usrSession = authentication.credentials as UserRequest
        return doUserLookup(usrSession)

    }

    override fun additionalAuthenticationChecks(
        userDetails: UserDetails,
        authentication: UsernamePasswordAuthenticationToken
    ) {
        if (authentication.credentials == null) {
            logger.debug("Authentication failed: no credentials provided")
            throw BadCredentialsException(
                messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"
                )
            )
        }

        val usrSession = authentication.credentials as UserRequest
        logger.debug("We have our user set as ${usrSession.username} in ${usrSession.client}")

        if (!encoder.matches(usrSession.password, userDetails.password)) {
            throw BadCredentialsException(
                "Bad Credentials for ${userDetails.username}. Authentication failed"
            )
        }

    }

    private fun doUserLookup(user: UserRequest): UserDetails {

        val ds = dsService.getSourceMap()[user.client]
            ?: throw BadCredentialsException(
                "Datasource for ${user.client} does not exist. Authentication failed"
            )

        val result = JdbcTemplate(ds).queryForRowSet("SELECT * FROM USERS WHERE USERNAME = ?", user.username)

        if (!result.next()) throw BadCredentialsException(
            "Bad Credentials for ${user.username}. Authentication failed"
        )

        val userId = result.getLong("id")
        val fullName = result.getString("full_name").orEmpty()
        val pwd = result.getString("password")!!
        return User.Builder(userId, user.username, pwd, roles = arrayListOf(Role(id = 1L, roleName = "ADMIN")))
            .fullName(fullName)
            .client(user.client)
            .build()
    }

}