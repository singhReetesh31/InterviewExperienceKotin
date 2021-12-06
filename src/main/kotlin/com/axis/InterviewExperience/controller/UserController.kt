package com.axis.InterviewExperience.controller

import com.axis.InterviewExperience.dto.UserDto
import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.service.UserServiceImpl
import com.axis.InterviewExperience.utils.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/interviewExperience/user")
class UserController {
    @Autowired
    lateinit var service: UserServiceImpl
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var jwtUtils: JwtUtils
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
    @PostMapping("/addUser")
    fun addUser(@RequestBody userDto: UserDto):ResponseEntity<Any>{
        try {
            service.addUser(userDto)
            return ResponseEntity(HttpStatus.OK)
        }catch (exception:java.lang.Exception){
            throw exception
        }
    }
    @PostMapping("/authenticate")
    fun generateToken(@RequestBody userDto: UserDto): String? {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userDto.userName,userDto.password))

        }catch (exception:Exception){
            throw java.lang.Exception("Invalid username/password")
        }
        println("==================${userDto.userName}")
        return jwtUtils.generateToken(userDto.userName)
    }
}