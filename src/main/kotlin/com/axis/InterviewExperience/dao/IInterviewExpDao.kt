package com.axis.InterviewExperience.dao

import com.axis.InterviewExperience.model.InterviewExperienceForm
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface IInterviewExpDao: MongoRepository<InterviewExperienceForm,String> {
    fun getFormByUserName(userName:String):List<InterviewExperienceForm>
}