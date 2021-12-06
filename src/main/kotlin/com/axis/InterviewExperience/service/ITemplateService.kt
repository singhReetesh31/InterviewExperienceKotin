package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dto.TemplateDto
import com.axis.InterviewExperience.model.Comment
import com.axis.InterviewExperience.model.Template

interface ITemplateService {
   fun getTemplates():List<Template>
   fun updateTemplate(templateDto: TemplateDto)
   fun postComment(comment: Comment,templateId:String)
}