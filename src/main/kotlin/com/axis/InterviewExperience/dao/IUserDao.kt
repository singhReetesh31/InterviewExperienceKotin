package com.axis.InterviewExperience.dao

import com.axis.InterviewExperience.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface IUserDao:MongoRepository<User,String> {
    fun getUserByUserName(userName:String):Optional<User>
}