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

package com.vivid.graff.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import com.vivid.graff.MultiDataSource
import com.vivid.graff.security.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint


@Configuration
@EnableWebSecurity
@EnableEncryptableProperties
class VividWebSecurity
(private val dsService: MultiDataSource,
 private val encoder: PasswordEncoder,
 @Value("\${jwt.secret.passphrase}") private val passphrase :String,
 private val encryptService: EncryptService) : WebSecurityConfigurerAdapter() {



    @Bean("authenticationManager")
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun authService(): AuthService {
        return AuthService(authenticationManagerBean())
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        return VividAuthenticationProvider(dsService,encoder)
    }

    override fun configure(http: HttpSecurity) {
        http.exceptionHandling()
                .authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/auth/jwt/login",
                    "/index.html","/","/*.js","/*.css",
                    "/favicon.ico","/*.ttf","/*.woff").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .authenticationProvider(authenticationProvider())
                .addFilter(JWTAuthenticationFilter(authService(),jwtProperties()))
                .addFilter(JWTAuthorizationFilter(authenticationManager(),jwtProperties()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    fun jwtProperties():JwtSettings {
        return JwtSettings(passphrase,"Authorization","auth-session","Bearer ",1000L*60*30)
    }


}