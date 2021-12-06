package com.axis.InterviewExperience.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function


@Service
class JwtUtils {
    private var secret:String="reeteshSingh"

    fun extractUsername(token: String?): String {
        return extractClaim(token) { obj: Claims -> obj.subject }
    }

    fun extractExpiration(token: String?): Date {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }

    fun <T> extractClaim(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String?): Boolean? {
        return extractExpiration(token).before(Date())
    }

    fun generateToken(username: String): String? {
        val claims: kotlin.collections.Map<String, Any> = HashMap()
        return createToken(claims, username)
    }

    private fun createToken(claims: kotlin.collections.Map<String, Any>, subject: String): String? {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    fun validateToken(token: String?, userDetails: UserDetails): Boolean? {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }
}