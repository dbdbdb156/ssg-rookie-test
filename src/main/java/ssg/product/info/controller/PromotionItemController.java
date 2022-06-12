package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.dto.PromotionItemDTO;
import ssg.product.info.dto.RequestIdDTO;
import ssg.product.info.exception.NoExistPromotionItemException;
import ssg.product.info.service.PromotionItemService;

@RestController
@RequiredArgsConstructor
public class PromotionItemController {
    private final PromotionItemService promotionItemService;

    @PostMapping("/api/promotionitem")
    public ResponseEntity inputPromotionItem(PromotionItemDTO promotionItemDTO){
        promotionItemService.createNewPromotionItem(promotionItemDTO);
        return new ResponseEntity("make PromotionItem ", HttpStatus.CREATED);
    }

    @DeleteMapping("/api/promotionitem")
    public ResponseEntity deleteUser(RequestIdDTO promotionItemidDTO) throws NoExistPromotionItemException {
        promotionItemService.deletePromotionItem(promotionItemidDTO.getId());
        return new ResponseEntity("delete promotionItem "+promotionItemidDTO.getId(),HttpStatus.NO_CONTENT);
    }

}
