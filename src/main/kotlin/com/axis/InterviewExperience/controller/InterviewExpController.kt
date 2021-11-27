package com.axis.InterviewExperience.controller

import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.service.IInterviewExpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/interviewExp")
class InterviewExpController {
    @Autowired
    lateinit var service:IInterviewExpService

    @PostMapping("/addForm")
    fun addForm(@RequestBody form:InterviewExperienceForm){
        service.addForm(form)
    }
    @GetMapping("/getForms")
    fun getForms():ResponseEntity<List<InterviewExperienceForm>>{
        return ResponseEntity(service.getForms(),HttpStatus.OK)
    }

    @GetMapping("/getFormByUserName/{userName}")
    fun getFormByUserName(@PathVariable userName:String):ResponseEntity<Any>{
         val optional:Optional<InterviewExperienceForm?>
         optional=service.getFormUserName(userName)
         if (optional.isPresent){
             return ResponseEntity(optional.get(),HttpStatus.OK)
         }
        return ResponseEntity("User Name Not Found",HttpStatus.BAD_REQUEST)
    }
}