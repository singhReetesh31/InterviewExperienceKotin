package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.model.Template

interface ITemplateService {
   fun getTemplates():List<Template>
   fun updateTemplate(template: Template)
}