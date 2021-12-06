package com.axis.InterviewExperience.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    val id:String?=null,
    var userName:String,
    var password:String,

) {
    var isActive:Boolean=true
    var role:String="ROLE_USER"
}