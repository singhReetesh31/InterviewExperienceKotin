package com.axis.InterviewExperience.dao

import com.axis.InterviewExperience.model.InterviewExperienceForm
import org.springframework.data.mongodb.repository.MongoRepository

interface IInterviewExpDao: MongoRepository<InterviewExperienceForm,String> {
}