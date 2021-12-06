package com.axis.InterviewExperience.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap
//@Document(collection = "comments")
data class Comment (
    //var templateId:String,
     //key=userName,value=comment
    //@Id
   // val id: String?,
    var userName:String,
    var comment: String?,
    var name: String,
  //  var templateId:String
      ){
    /*@Transient
    var formatter=SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    @Transient
    var date=Date()
    var strDAte=formatter.format(date)*/
//  date shouldn't enter by user
     var date:String=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))


}