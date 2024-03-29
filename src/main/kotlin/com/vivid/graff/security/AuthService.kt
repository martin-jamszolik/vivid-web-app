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

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler

class AuthService(private val authManager: AuthenticationManager) {


    @Throws(BadCredentialsException::class, ServletException::class)
    fun login(requestUser: UserRequest): Authentication {

        val authenticationTokenRequest = UsernamePasswordAuthenticationToken(requestUser.username, requestUser)

        val authentication: Authentication = this.authManager.authenticate(authenticationTokenRequest)
        val securityContext = SecurityContextHolder.getContext()
        securityContext.authentication = authentication
        return authentication

    }


    fun logout(request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<Any> {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            SecurityContextLogoutHandler().logout(
                request,
                response,
                authentication
            )
        }
        return ResponseEntity(HttpStatus.OK)
    }
}