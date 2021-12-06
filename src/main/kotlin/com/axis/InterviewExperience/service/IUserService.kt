package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dto.UserDto
import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.model.User
import java.util.*

interface IUserService {
    fun addForm(form: InterviewExperienceForm)
    fun getForms():List<InterviewExperienceForm>
    fun getFormByUserName(userName:String):List<InterviewExperienceForm>
    fun deleteForm(id:String)
    fun updateForm(id: String,form: InterviewExperienceForm)
    fun addUser(userDto: UserDto)
}