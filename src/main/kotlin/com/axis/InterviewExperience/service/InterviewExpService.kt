package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dao.IInterviewExpDao
import com.axis.InterviewExperience.model.InterviewExperienceForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class InterviewExpService(@Autowired val dao: IInterviewExpDao):IInterviewExpService{
    override fun addForm(form: InterviewExperienceForm) {
        dao.save(form)
    }

    override fun getForms(): List<InterviewExperienceForm> {
        return dao.findAll()
    }

    override fun getFormUserName(userName: String): Optional<InterviewExperienceForm?> {
       return dao.findById(userName)
    }
}