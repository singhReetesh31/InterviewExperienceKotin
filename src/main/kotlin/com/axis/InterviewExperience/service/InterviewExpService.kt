package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dao.IInterviewExpDao
import com.axis.InterviewExperience.dao.TemplateDao
import com.axis.InterviewExperience.exception.IdNotFoundException
import com.axis.InterviewExperience.exception.UserNameNotFound
import com.axis.InterviewExperience.model.Comment
import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.model.Template
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap

@Service
class InterviewExpService(@Autowired val dao: IInterviewExpDao):IInterviewExpService{
    @Autowired
    lateinit var templateDao: TemplateDao

    override fun addForm(form: InterviewExperienceForm) {
        //validateNullValueAndDuplicateValue(form)
       //val existForm: List<InterviewExperienceForm> =dao.getFormByUserName(form.userName)
        var template:Template
        dao.save(form)//id is null
        var comment=Comment(HashMap(),"")
        var comments:MutableList<Comment?> = mutableListOf<Comment?>()
        comments.add(comment)
        //var mutableListInt: MutableList<Int> = mutableListOf<Int>()
        //comments.add(comment)
       val formWithId:InterviewExperienceForm = dao.findTopByOrderByIdDesc()//id generated by mongodb
        //println("form $form")
        /*for (form in forms){
            template= Template(null,0,comment,0,null,form.id)
            templateDao.save(template)
        }*/
       template=Template(null,0,comments,0,"none",formWithId)
        templateDao.save(template)

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