package ssg.product.info.exception;

public class DiscountPolicyNotExistException extends RuntimeException{
    public DiscountPolicyNotExistException(String msg){
        super(msg);
    }
}
