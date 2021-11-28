package com.axis.InterviewExperience.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(UserNameAlreadyExist::class)
    fun UserNameAlreadyExistHandler(exception: Exception):ResponseEntity<Any>{
        return ResponseEntity(exception.message,HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler(UserNameNotFound::class)
    fun UserNameNotFoundHandler(exception: Exception):ResponseEntity<Any>{
        return ResponseEntity(exception.message,HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(IdNotFoundException::class)
    fun IdNotFoundExceptionHandler(exception: Exception):ResponseEntity<Any>{
        return ResponseEntity(exception.message,HttpStatus.NOT_FOUND)
    }
}