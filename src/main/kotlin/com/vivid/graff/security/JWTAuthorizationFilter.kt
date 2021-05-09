package com.woloxJwt.woloxJwt.security


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.vivid.graff.security.JwtSettings
import com.vivid.graff.security.Role
import com.vivid.graff.security.User
import com.vivid.graff.security.UserRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter( authManager: AuthenticationManager, private val settings:JwtSettings) : BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = request.getHeader(settings.header)

        if (header == null || !header.startsWith(settings.prefix)) {
            chain.doFilter(request, response)
            return
        }
        val authentication = authorize(request)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun authorize(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(settings.header)
        if (token != null) {
            // parse the token.
            val userClaims = JWT.require(Algorithm.HMAC512(settings.key))
                .build()
                .verify(token.replace(settings.prefix, ""))
                .claims
            return if (userClaims != null) {
                var usr = User.Builder(userClaims["userId"]!!.asLong(),
                    userClaims["username"]!!.asString(),
                    userClaims["password"]!!.asString(),
                    roles = arrayListOf(Role(id = 1L, roleName = "ADMIN")))
                        .fullName(userClaims["fullName"]!!.asString())
                        .client(userClaims["client"]!!.asString())
                        .build()

                UsernamePasswordAuthenticationToken(usr,null,usr.roles)
            } else null
        }

        return null
    }
}