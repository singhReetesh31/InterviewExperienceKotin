package com.axis.InterviewExperience.model

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

data class Comment (
    var comment:HashMap<String,String> //key=userName,value=comment
      ,var name: String  ){
    /*@Transient
    var formatter=SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    @Transient
    var date=Date()
    var strDAte=formatter.format(date)*/
// date shouldn't enter by user
     var date:String=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))


}