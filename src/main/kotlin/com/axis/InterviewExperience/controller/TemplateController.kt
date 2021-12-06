package com.axis.InterviewExperience.controller

import com.axis.InterviewExperience.dto.TemplateDto
import com.axis.InterviewExperience.model.Comment
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
    @GetMapping("/all")
    fun getTemplates():ResponseEntity<List<Template>>{
      return ResponseEntity(service.getTemplates(),HttpStatus.OK)
    }
    @PutMapping("/updateTemplate")
    fun updateTemplate(@RequestBody templateDto: TemplateDto):ResponseEntity<Any>{
        service.updateTemplate(templateDto)
        return ResponseEntity(HttpStatus.OK)
    }
    @PostMapping("/postComment/{templateId}")
    fun postComment(@RequestBody comment: Comment, @PathVariable templateId:String):ResponseEntity<Any>{
        try {
            service.postComment(comment,templateId)
        }catch (exception:Exception){
            throw exception
        }
        return ResponseEntity(HttpStatus.OK)
    }
}