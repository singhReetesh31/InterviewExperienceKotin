package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dao.IInterviewExpDao
import com.axis.InterviewExperience.exception.IdNotFoundException
import com.axis.InterviewExperience.exception.UserNameNotFound
import com.axis.InterviewExperience.model.InterviewExperienceForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class InterviewExpService(@Autowired val dao: IInterviewExpDao):IInterviewExpService{

    override fun addForm(form: InterviewExperienceForm) {
        //validateNullValue(form)
        val existForm: List<InterviewExperienceForm> =dao.getFormByUserName(form.userName)
        if (existForm.isEmpty()) {
            dao.save(form)
        }
        else{
            //validateDuplication(form)
            dao.save(form)
        }

    }

    override fun getForms(): List<InterviewExperienceForm> {
        return dao.findAll()
    }

    override fun getFormByUserName(userName: String): List<InterviewExperienceForm> {
        val form:List<InterviewExperienceForm> =dao.getFormByUserName(userName)
        if (form.isEmpty()) {
            throw UserNameNotFound("user name $userName not found")
        }
       return form
    }

    override fun deleteForm(id: String) {
         val optionalForm:Optional<InterviewExperienceForm> =dao.findById(id)
        if (optionalForm.isEmpty){
            throw IdNotFoundException("User not found with this id")
        }
        dao.deleteById(id)
    }

    override fun updateForm(id: String, form: InterviewExperienceForm) {
        val optionalForm:Optional<InterviewExperienceForm> =dao.findById(id)
        if (optionalForm.isPresent){
            if (optionalForm.get().userName.equals(form.userName))
                dao.save(form)
            else
                throw IdNotFoundException("User not found with this id or id and userName mismatch")
        }
    }
    /* fun getFormUserName1(userName: String): InterviewExperienceForm? {
         return dao.findByIdOrNull(userName)
     }*/

}