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

import com.vivid.graff.SubdomainUtil
import com.vivid.graff.UserSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {


    @GetMapping("/user")
    fun user(user: Principal): Principal {
        return user
    }

    @PostMapping("/login")
    fun processLogin(
        @RequestBody requestUser: UserSession,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ResponseEntity<User> {
        val client = SubdomainUtil.subdomain(request.serverName)
        requestUser.client = client
        return authService.login(requestUser)
    }

    @GetMapping("/logout")
    fun processLogout(request: HttpServletRequest, resp: HttpServletResponse): ResponseEntity<Any> {
        return authService.logout(request, resp)
    }

}