package com.axis.InterviewExperience.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDate
@Document(collection = "template")
data class Template(
    @Id
    val id: String?=null,
    var like:Int?,
   // @DocumentReference
    var comments:MutableList<Comment>,//can't perform edit or delete
    var views:Int?,
    var report:String?,//report an issue with this template
   // @DBRef(db="interview_experience_form")
    @DocumentReference
    val form: InterviewExperienceForm
        ){
    fun addComment(comment: Comment){
        comments.add(comment)
    }
    fun deleteComment(comment: Comment){
        comments.remove(comment)
    }
}