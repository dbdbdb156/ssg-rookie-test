package ssg.product.info.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ssg.product.info.dto.ErrorDTO;
import ssg.product.info.dto.ResponseDTO;
import ssg.product.info.exception.*;

@ControllerAdvice
public class ExceptionHandlingController extends Exception{

    @ExceptionHandler(NoExistUserException.class)
    public ResponseEntity userExistError(){
        return new ResponseEntity("유저가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoExistItemException.class)
    public ResponseEntity itemExistError(){
        return new ResponseEntity("상품이 존재하지 않습니다",HttpStatus.NOT_FOUND);
    }
    // 404 자원 없음.
    @ExceptionHandler(NoExistPromotionException.class)
    public ResponseEntity promotionExistError(){
        return new ResponseEntity("프로모션이 존재하지 않습니다",HttpStatus.NOT_FOUND);
    }
    // 401 권한 없음
    @ExceptionHandler(WithdrawalException.class)
    public ResponseEntity withdrawalError(){
        return new ResponseEntity("유저가 탈퇴하였습니다",HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoExistPromotionItemException.class)
    public ResponseEntity promotionItemExistError(){
        ResponseDTO body = new ResponseDTO(false, null, new ErrorDTO(HttpStatus.NOT_FOUND, "프로모션상품이 존재하지 않습니다"));
        return new ResponseEntity(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DiscountPolicyNotExistException.class)
    public ResponseEntity discountPolicyNotExist(){
        return new ResponseEntity("",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MultiDiscountPolicyException.class)
    public ResponseEntity multiDiscountPolicy(){
        return new ResponseEntity("",HttpStatus.NOT_FOUND);
    }




}
