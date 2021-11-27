package com.axis.InterviewExperience.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "inerview_experience_form")
data class InterviewExperienceForm (
    @Id
    val userName:String?=null,
    var company:String,
    var role:String,
    var isSelected:Boolean,
    var jobLocation:String,
    var postAsAnonymous:Boolean,
    var applyMode:String,
    var sourceOfOffCampus:String?,
    var eligibilityCriteria:String,
    var noOfRounds:Int?,
    var rounds:MutableList<Rounds?>
        )

