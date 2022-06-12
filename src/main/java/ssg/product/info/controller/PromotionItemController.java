package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.dto.PromotionItemDTO;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistPromotionItemException;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.service.PromotionItemService;
import ssg.product.info.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PromotionItemController {
    private final PromotionItemService promotionItemService;

    @PostMapping("/api/promotionitem")
    public ResponseEntity inputPromotionItem(HttpServletRequest req, PromotionItemDTO promotionItemDTO){
        promotionItemService.createNewPromotionItem(promotionItemDTO);
        return new ResponseEntity("make PromotionItem ", HttpStatus.CREATED);
    }

    @DeleteMapping("/api/promotionitem/{promotionitemid}")
    public ResponseEntity deleteUser(HttpServletRequest req, @PathVariable(value="promotionitemid") Long id) throws NoExistPromotionItemException {
        promotionItemService.deletePromotionItem(id);
        return new ResponseEntity("delete promotionItem "+id,HttpStatus.NO_CONTENT);
    }

}
