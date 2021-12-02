package com.axis.InterviewExperience.dao

import com.axis.InterviewExperience.model.InterviewExperienceForm
import com.mongodb.client.MongoCollection
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*


interface IInterviewExpDao: MongoRepository<InterviewExperienceForm,String> {
    fun getFormByUserName(userName:String):List<InterviewExperienceForm>
   // db.inerview_experience_form.find().limit(1).sort({$natural:-1}).pretty()
  // @Query(value = "{'${naturalOrder<Comparator<InterviewExperienceForm.>>()}': ?-1}")
    //@Query(sort="{${naturalOrder<>()}}")
    //Query query = new Query().with(new Sort(Direction.ASC, "$natural"));
  // @Query
  // public fun <InterviewExperienceForm : Comparable<InterviewExperienceForm>> naturalOrder(): Comparator<InterviewExperienceForm>
    //fun findLastInsertedForm(natural: String):InterviewExperienceForm?
fun findTopByOrderByIdDesc():InterviewExperienceForm

}