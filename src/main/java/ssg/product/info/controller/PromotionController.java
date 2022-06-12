package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.Promotion;
import ssg.product.info.dto.ItemPromotionInfoDTO;
import ssg.product.info.dto.PromotionDTO;
import ssg.product.info.dto.RequestIdDTO;
import ssg.product.info.exception.DiscountPolicyNotExistException;
import ssg.product.info.exception.MultiDiscountPolicyException;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.exception.NoExistPromotionException;
import ssg.product.info.service.ItemService;
import ssg.product.info.service.PromotionItemService;
import ssg.product.info.service.PromotionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;
    private final ItemService itemService;
    private final PromotionItemService promotionItemService;

    @PostMapping("/api/promotion")
    public ResponseEntity inputUser(PromotionDTO promotionDTO){
        promotionService.createNewPromotion(promotionDTO);
        return new ResponseEntity("make promotion "+promotionDTO.getPromotionNm(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/promotion")
    public ResponseEntity deleteUser(RequestIdDTO promotionidDTO) throws NoExistPromotionException {
        promotionService.deletePromotion(promotionidDTO.getId());
        return new ResponseEntity("delete promotion "+promotionidDTO.getId(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/api/promotions")
    public ResponseEntity applyPromotionsToItem(RequestIdDTO itemidDTO) throws NoExistItemException, NoExistPromotionException, DiscountPolicyNotExistException, MultiDiscountPolicyException {

        Optional<Item> item = itemService.getItem(itemidDTO.getId());
        itemService.isItemExist(item);

        List<Long> promotionsId = promotionItemService.promotionsForItem(item.get().getId());
        List<Promotion> promotions = promotionService.promotionsForItems(promotionsId);
        ItemPromotionInfoDTO info = promotionService.choiceBestPromotion(item.get(), promotions);

        return new ResponseEntity(info,HttpStatus.OK);
    }


}
