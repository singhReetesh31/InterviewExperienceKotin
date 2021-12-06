package com.axis.InterviewExperience.filter

import com.axis.InterviewExperience.service.MyUserDetailsService
import com.axis.InterviewExperience.utils.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
@Component
class JwtFilter: OncePerRequestFilter() {
    @Autowired
    lateinit var jwtUtils:JwtUtils
    @Autowired
    lateinit var service:MyUserDetailsService
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader("Authorization")
        var token: String? = null
        var userName: String? = null
        //Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZWV0ZXNoQGdtYWlsLmNvbSIsImV4cCI6MTYzODY1NzY1MiwiaWF0IjoxNjM4NjM5NjUyfQ.Eb_UKdVt_hM0lgimA4QvmAT-vYvjsTP7E6sNQhotX8KEIyH33iTWWuOpgUf0FAmBrzLRGqVYwwZ1tyWkAw9yHQ
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            token = authorizationHeader.substring(7);//otherwise give null exception
            userName = jwtUtils.extractUsername(token)
            println("===userNAme: " + userName);

        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = service.loadUserByUsername(userName);
            if (jwtUtils.validateToken(token, userDetails)!!) {
                System.out.println("TOken is validated");
                var usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                    .setDetails(WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}