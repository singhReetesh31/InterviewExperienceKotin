package com.axis.InterviewExperience.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors;
class MyUserDetails: UserDetails {
    private var userName:String
    private var password:String
    private var isActive:Boolean
    private var role:MutableList<GrantedAuthority> = mutableListOf()
    constructor(user: User){
        this.userName=user.userName
        this.password=user.password
        this.isActive=user.isActive

        var arr= user.role.split(",")
        for (obj in arr){
            var sgaObj=SimpleGrantedAuthority(obj)
            this.role.add(sgaObj)
        }


    }
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
      return role
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return  userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return isActive
    }
}