package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dao.IUserInterviewExpFormDao
import com.axis.InterviewExperience.dao.ITemplateDao
import com.axis.InterviewExperience.dao.IUserDao
import com.axis.InterviewExperience.dto.UserDto
import com.axis.InterviewExperience.exception.IdNotFoundException
import com.axis.InterviewExperience.exception.UserNameAlreadyExist
import com.axis.InterviewExperience.exception.UserNameNotFound
import com.axis.InterviewExperience.model.Comment
import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.axis.InterviewExperience.model.Template
import com.axis.InterviewExperience.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(@Autowired val formDao: IUserInterviewExpFormDao):IUserService{
    @Autowired
    lateinit var ITemplateDao: ITemplateDao
    @Autowired
    lateinit var userDao: IUserDao;


    override fun addForm(form: InterviewExperienceForm) {
        //validateNullValueAndDuplicateValue(form)
       //val existForm: List<InterviewExperienceForm> =dao.getFormByUserName(form.userName)
        var template:Template
        formDao.save(form)//id is null
       // var comment=Comment(HashMap(),"")
        //var comments = mutableListOf<Comment>()
        //comments.add(comment)
        var comments: MutableList<Comment> = mutableListOf<Comment>()
        //comments.add(comment)
       // var comments = commentDao.getCommentByTemplateId()
       val formWithId:InterviewExperienceForm = formDao.findTopByOrderByIdDesc()//id generated by mongodb
        //println("form $form")
        /*for (form in forms){
            template= Template(null,0,comment,0,null,form.id)
            templateDao.save(template)
        }*/
       template=Template(null,0, comments =comments ,0,null,formWithId)
        ITemplateDao.save(template)

    }

    override fun getForms(): List<InterviewExperienceForm> {
        return formDao.findAll()
    }

    override fun getFormByUserName(userName: String): List<InterviewExperienceForm> {
        val form:List<InterviewExperienceForm> =formDao.getFormByUserName(userName)
        if (form.isEmpty()) {
            throw UserNameNotFound("user name $userName not found")
        }
       return form
    }

    override fun deleteForm(id: String) {
         val optionalForm:Optional<InterviewExperienceForm> =formDao.findById(id)
        if (optionalForm.isEmpty){
            throw IdNotFoundException("User not found with this id")
        }
        formDao.deleteById(id)
    }

    override fun updateForm(id: String, form: InterviewExperienceForm) {
        val optionalForm:Optional<InterviewExperienceForm> =formDao.findById(id)
        if (optionalForm.isPresent){
            if (optionalForm.get().userName.equals(form.userName))
                formDao.save(form)
            else
                throw IdNotFoundException("User not found with this id or id and userName mismatch")
        }
    }

    override fun addUser(userDto: UserDto) {
       var optionalUser =userDao.getUserByUserName(userDto.userName)
        if (optionalUser.isPresent){
            throw UserNameAlreadyExist("username already exist! Please login")
        }
        val user=User(null,userDto.userName,userDto.password)
        userDao.save(user)
    }

    /* fun getFormUserName1(userName: String): InterviewExperienceForm? {
         return dao.findByIdOrNull(userName)
     }*/

}