package com.axis.InterviewExperience.controller

import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.model.Template
import com.axis.InterviewExperience.service.ITemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/interviewExperience")
class TemplateController {
    @Autowired
   lateinit var service:ITemplateService
    @GetMapping("/")
    fun getTemplates():ResponseEntity<List<Template>>{
      return ResponseEntity(service.getTemplates(),HttpStatus.OK)
    }
    @PutMapping("/updateTemplate")
    fun updateTemplate(@RequestBody template: Template):ResponseEntity<Any>{
        service.updateTemplate(template)
        return ResponseEntity(HttpStatus.OK)
    }
}