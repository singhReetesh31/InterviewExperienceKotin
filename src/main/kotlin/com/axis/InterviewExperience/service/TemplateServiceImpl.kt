package com.axis.InterviewExperience.service

import com.axis.InterviewExperience.dao.ITemplateDao
import com.axis.InterviewExperience.dto.TemplateDto
import com.axis.InterviewExperience.exception.IdNotFoundException
import com.axis.InterviewExperience.model.Comment
import com.axis.InterviewExperience.model.Template
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TemplateServiceImpl :ITemplateService{
    @Autowired
    lateinit var dao:ITemplateDao
    override fun getTemplates(): List<Template> {

        return dao.findAll()
    }

    override fun updateTemplate(templateDto: TemplateDto){
        val optionalTemplate=dao.findById(templateDto.id!!)
        if (optionalTemplate.isPresent){
         /* var comments:HashMap<String,Comment> =  optionalTemplate.get().comments
            comments.put(templateDto.comment.userName,templateDto.comment)
            var template:Template= Template(templateDto.id,
                templateDto.like,
                comments,
                templateDto.views,
                templateDto.report,
                templateDto.form)
            dao.save(template)*/
        }else{
            throw IdNotFoundException("No Id Found to Update/post doesn't exist anymore")
        }
    }

    override fun postComment(comment: Comment,templateId:String) {
        var optionalTemplate=dao.findById(templateId)
        if (optionalTemplate.isPresent){
            var template=optionalTemplate.get()
            var existedComments=template.comments
            for (existedComment in existedComments){
                //println("===================\n=====${existedComment.userName}==${comment.userName}====######${existedComment.userName.equals(comment)}")
                if (existedComment.userName.equals(comment.userName)){
                    println("===================\n===========######")
                    existedComments.remove(existedComment)
                    break
                }
            }
            /*if (existedComments.contains(comment)){
                println("==============================######")
                existedComments.remove(comment)
            }*/
            //template.addComment(comment)
            existedComments.add(comment)
            dao.save(template)

        }else{
            throw IdNotFoundException("Template id not found/doesn't exist")
        }
    }
}