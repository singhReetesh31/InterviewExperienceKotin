package com.axis.InterviewExperience.config

import com.axis.InterviewExperience.filter.JwtFilter
import com.axis.InterviewExperience.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var userDetailsService:MyUserDetailsService
    @Autowired
    lateinit var jwtFilter:JwtFilter
    override fun configure(auth: AuthenticationManagerBuilder?) {
        if (auth != null) {
            println("Inside AuthenticationManagerBuilder")
            auth.userDetailsService(userDetailsService)
        }
    }
    @Bean
    fun getPasswordEncoder():PasswordEncoder{
        return NoOpPasswordEncoder.getInstance()
    }
    @Bean(name= arrayOf(BeanIds.AUTHENTICATION_MANAGER))
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            println("Inside HttpSecurity")
            http.csrf().disable().authorizeRequests()
                .antMatchers(
                    "/interviewExperience/user/authenticate",
                    "/interviewExperience/all",
                    "/interviewExperience/user/addUser"
                ).permitAll()
                .and().authorizeRequests().antMatchers(
                    "/interviewExperience/user/addForm",
                    "/interviewExperience/user/getForm/{userName",
                    "/interviewExperience/user/deleteForm/{id}",
                    "/interviewExperience/user/updateForm/{id}",
                ).hasRole("USER")
                .anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        }
    }
}