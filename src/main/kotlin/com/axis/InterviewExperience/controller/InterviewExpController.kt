package com.axis.InterviewExperience.controller

import com.axis.InterviewExperience.exception.UserNameAlreadyExist
import com.axis.InterviewExperience.exception.UserNameNotFound
import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.model.Template
import com.axis.InterviewExperience.service.IInterviewExpService
import com.axis.InterviewExperience.service.InterviewExpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("")
class InterviewExpController {
    @Autowired
    lateinit var service:InterviewExpService

    @GetMapping("/")
    fun getForms():ResponseEntity<List<InterviewExperienceForm>>{
        return ResponseEntity(service.getForms(),HttpStatus.OK)
    }
    @PostMapping("/addTemplate")
    fun addTemplate(@RequestBody template: Template){

    }
}