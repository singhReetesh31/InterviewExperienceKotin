package com.axis.InterviewExperience.dto

import com.axis.InterviewExperience.model.Comment
import com.axis.InterviewExperience.model.InterviewExperienceForm
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference


data class TemplateDto(
    val id: String?=null,
    var like:Int?,
    var comment: Comment,// for posting comment there should be one user/userName
    var views:Int?,
    var report:String?,//report an issue with this template
   // @DBRef(db="interview_experience_form")
    //@DocumentReference
    val form: InterviewExperienceForm
        )