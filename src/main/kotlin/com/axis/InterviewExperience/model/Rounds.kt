package com.axis.InterviewExperience.model

import java.time.LocalDate




class Rounds (
    var roundType: String,
    var levelOfDifficulty:Int,

    //@JsonFormat(pattern = "yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
    //@Temporal(TemporalType.DATE)
    var date: LocalDate,
    var duration:Double,
    var testPlatform: String,
    var noOfQuestions:Int,
    var questionDescription: String,
    var preperationTips: String
)