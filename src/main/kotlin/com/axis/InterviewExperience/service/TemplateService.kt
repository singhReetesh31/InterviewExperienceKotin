package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dao.TemplateDao
import com.axis.InterviewExperience.exception.IdNotFoundException
import com.axis.InterviewExperience.model.Template
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TemplateService :ITemplateService{
    @Autowired
    lateinit var dao:TemplateDao
    override fun getTemplates(): List<Template> {
        return dao.findAll()
    }

    override fun updateTemplate(template: Template){
        val optionalTemplate=dao.findById(template.id!!)
        if (optionalTemplate.isPresent){
            dao.save(template)
        }else{
            throw IdNotFoundException("No Id Found to Update")
        }
    }
}