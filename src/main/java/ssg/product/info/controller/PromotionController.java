package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.Promotion;
import ssg.product.info.dto.*;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.exception.NoExistPromotionException;
import ssg.product.info.service.ItemService;
import ssg.product.info.service.PromotionItemService;
import ssg.product.info.service.PromotionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class PromotionController {
    private final PromotionService promotionService;
    private final ItemService itemService;
    private final PromotionItemService promotionItemService;

    @PostMapping("/promotion")
    public ResponseEntity inputUser(PromotionDTO promotionDTO){
        Promotion promotion = promotionService.createNewPromotion(promotionDTO);
        ResponseDTO body = new ResponseDTO(true, new ContentDTO(201L,HttpStatus.CREATED,"make promotion",promotion), null);
        return new ResponseEntity(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/promotion")
    public ResponseEntity deleteUser(RequestIdDTO promotionidDTO) throws NoExistPromotionException {
        promotionService.deletePromotion(promotionidDTO.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/promotions")
    public ResponseEntity applyPromotionsToItem(RequestIdDTO itemidDTO) throws NoExistItemException, NoExistPromotionException{

        Optional<Item> item = itemService.getItem(itemidDTO.getId());
        itemService.isItemExist(item);

        List<Long> promotionsId = promotionItemService.promotionsForItem(item.get().getId());
        List<Promotion> promotions = promotionService.promotionsForItems(promotionsId);
        ItemPromotionInfoDTO info = promotionService.choiceBestPromotion(item.get(), promotions);

        ResponseDTO body = new ResponseDTO(true, new ContentDTO(200L,HttpStatus.OK,"apply promotion",info), null);
        return new ResponseEntity(body,HttpStatus.OK);
    }


}
