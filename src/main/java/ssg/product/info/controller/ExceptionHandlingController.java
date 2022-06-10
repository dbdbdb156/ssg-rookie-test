package ssg.product.info.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ssg.product.info.exception.NoExistUserException;

@ControllerAdvice
public class ExceptionHandlingController extends Exception{

    @ExceptionHandler(NoExistUserException.class)
    public ResponseEntity userExistError(){
        return new ResponseEntity("유저가 존재하지 않아 삭제가 불가능합니다", HttpStatus.NOT_FOUND);
    }

}
