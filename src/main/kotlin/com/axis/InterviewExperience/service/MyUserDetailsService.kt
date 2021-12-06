package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dao.IUserDao
import com.axis.InterviewExperience.exception.FieldCanNotBeEmptyException
import com.axis.InterviewExperience.exception.UserNameNotFound
import com.axis.InterviewExperience.model.MyUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService:UserDetailsService {
    @Autowired
    lateinit var dao:IUserDao
    override fun loadUserByUsername(username: String?): UserDetails {

        if (username != null) {
            var optionalUser=dao.getUserByUserName(username)
            if (optionalUser.isPresent)
            return MyUserDetails(optionalUser.get())
            else
                throw UserNameNotFound("username NOT Found!")
        }else{
            throw FieldCanNotBeEmptyException("Username field can't be empty")
        }

    }
}