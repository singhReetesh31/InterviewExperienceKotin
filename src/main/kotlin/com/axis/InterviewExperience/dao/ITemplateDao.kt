package com.axis.InterviewExperience.dao

import com.axis.InterviewExperience.model.Template
import org.springframework.data.mongodb.repository.MongoRepository

interface ITemplateDao:MongoRepository<Template,String> {

}