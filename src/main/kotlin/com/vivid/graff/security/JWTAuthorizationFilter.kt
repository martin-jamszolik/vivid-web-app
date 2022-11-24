package com.vivid.graff.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import jakarta.servlet.FilterChain
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import jakarta.servlet.ServletException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse


class JWTAuthorizationFilter( authManager: AuthenticationManager, private val settings:JwtSettings) : BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = request.getHeader(settings.header)
        val cookie: Cookie?= getCookie(request,settings.cookie)

        if( cookie == null && header == null ){
            chain.doFilter(request, response)
            return
        }

        if (cookie == null && header !=null && !header.startsWith(settings.prefix)  ) {
            chain.doFilter(request, response)
            return
        }

        val authentication = authorize(request,cookie)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun authorize(request: HttpServletRequest, cookie: Cookie?): UsernamePasswordAuthenticationToken? {
        val token = if (cookie !=null)  cookie.value
                    else request.getHeader(settings.header).replace(settings.prefix, "")

        if (token != null) {
            // parse the token.
            val userClaims = JWT.require(Algorithm.HMAC512(settings.key))
                .build()
                .verify(token)
                .claims
            return if (userClaims != null) {
                var usr = User.Builder(userClaims["userId"]!!.asLong(),
                    userClaims["username"]!!.asString(),
                    "",
                    roles = arrayListOf(Role(id = 1L, roleName = "ADMIN")))
                        .fullName(userClaims["fullName"]!!.asString())
                        .client(userClaims["client"]!!.asString())
                        .build()

                UsernamePasswordAuthenticationToken(usr,null,usr.roles)
            } else null
        }

        return null
    }

    private fun getCookie(request: HttpServletRequest, name: String): Cookie? {
        val cookies = request.cookies
        if (cookies != null) {
            for (cookie in cookies) {
                if (cookie.name == name) {
                    return cookie
                }
            }
        }
        return null
    }
}