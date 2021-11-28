package com.axis.InterviewExperience.controller

import com.axis.InterviewExperience.exception.UserNameAlreadyExist
import com.axis.InterviewExperience.exception.UserNameNotFound
import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.service.InterviewExpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/interviewExperience/user")
class UserController {
    @Autowired
    lateinit var service: InterviewExpService

    @PostMapping("/addForm")
    fun addForm(@RequestBody form: InterviewExperienceForm): ResponseEntity<Any> {
       try {
           service.addForm(form)
       }catch (exception:Exception){
           throw exception
       }
        return ResponseEntity(HttpStatus.ACCEPTED)
    }
    @GetMapping("/getForm/{userName}")
    fun getFormByUserName(@PathVariable userName:String):ResponseEntity<Any>{
        /* var form:InterviewExperienceForm=service?.getFormUserName1(userName) ?: throw UserNameNotFound("user not found")
         return ResponseEntity(form,HttpStatus.OK)*/
        val form:List<InterviewExperienceForm>
        try {
            form=service.getFormByUserName(userName)
        }catch (exception:Exception){
            throw exception
        }
        return ResponseEntity(form,HttpStatus.OK)
    }
    @DeleteMapping("/deleteForm/{id}")
    fun deleteForm(@PathVariable id:String):ResponseEntity<Any>{
        try {
            service.deleteForm(id)
            return ResponseEntity(HttpStatus.OK)
        }catch (exception:Exception){
            throw exception
        }
    }
    @PutMapping("/updateForm/{id}")
    fun updateForm(@PathVariable id: String,@RequestBody form: InterviewExperienceForm):ResponseEntity<Any>{
        try {
            service.updateForm(id, form)
            return ResponseEntity(HttpStatus.OK)
        }catch (exception:Exception){
            throw exception
        }
    }
}