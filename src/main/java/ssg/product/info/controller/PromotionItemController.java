package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.domain.PromotionItem;
import ssg.product.info.dto.ContentDTO;
import ssg.product.info.dto.PromotionItemDTO;
import ssg.product.info.dto.RequestIdDTO;
import ssg.product.info.dto.ResponseDTO;
import ssg.product.info.exception.NoExistPromotionItemException;
import ssg.product.info.service.PromotionItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class PromotionItemController {
    private final PromotionItemService promotionItemService;

    @PostMapping("/promotionitem")
    public ResponseEntity inputPromotionItem(PromotionItemDTO promotionItemDTO){
        PromotionItem promotionItem = promotionItemService.createNewPromotionItem(promotionItemDTO);
        ResponseDTO body = new ResponseDTO(true, new ContentDTO(201L,HttpStatus.CREATED,"make promotionItem",promotionItem), null);
        return new ResponseEntity(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/promotionitem")
    public ResponseEntity deleteUser(RequestIdDTO promotionItemidDTO) throws NoExistPromotionItemException {
        promotionItemService.deletePromotionItem(promotionItemidDTO.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
