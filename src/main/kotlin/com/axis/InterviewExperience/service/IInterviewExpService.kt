package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.model.InterviewExperienceForm
import java.util.*

interface IInterviewExpService {
    fun addForm(form: InterviewExperienceForm)
    fun getForms():List<InterviewExperienceForm>
    fun getFormUserName(userName:String):Optional<InterviewExperienceForm?>
}