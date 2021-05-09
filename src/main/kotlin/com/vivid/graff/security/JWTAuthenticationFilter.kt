package com.vivid.graff.security


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.vivid.graff.SubdomainUtil
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthenticationFilter(
    private val service: AuthService,
    private val settings:JwtSettings) : UsernamePasswordAuthenticationFilter() {

    init {
        setFilterProcessesUrl("/auth/jwt/login");
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse
    ): Authentication {
        return try {
            val requestUser = ObjectMapper().readValue(
                req.inputStream,
                UserRequest::class.java
            )
            val client = SubdomainUtil.subdomain(req.serverName)
            requestUser.client = client
            service.login(requestUser)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain,
        auth: Authentication
    ) {
        val user = auth.principal as User
        val token = JWT.create()
            .withSubject("${user.client}-${user.username}")
            .withExpiresAt(Date(System.currentTimeMillis() + settings.expire))
            .withIssuedAt(Date())
            .withClaim("username",user.username)
            .withClaim("client",user.client)
            .withClaim("password",user.password)
            .withClaim("userId",user.id)
            .withClaim("fullName",user.fullName)
            .sign(Algorithm.HMAC512(settings.key))
        res.addHeader(settings.header, settings.prefix+token)
        val body= "${user.username} $token"

        res.writer.write(body)
        res.writer.flush()
    }
}