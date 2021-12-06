package com.axis.InterviewExperience.controller

import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.model.Template
import com.axis.InterviewExperience.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("")
class InterviewExpController {
    @Autowired
    lateinit var service:UserServiceImpl

    @GetMapping("/")
    fun getForms():ResponseEntity<List<InterviewExperienceForm>>{
        return ResponseEntity(service.getForms(),HttpStatus.OK)
    }
    @PostMapping("/addTemplate")
    fun addTemplate(@RequestBody template: Template){

    }
}