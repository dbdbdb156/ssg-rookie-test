package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.dto.PromotionDTO;
import ssg.product.info.exception.NoExistPromotionException;
import ssg.product.info.service.PromotionService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping("/api/promotion")
    public ResponseEntity inputUser(HttpServletRequest req,
                                    PromotionDTO promotionDTO
    ){
        promotionService.createNewPromotion(promotionDTO);
        return new ResponseEntity("make promotion "+promotionDTO.getPromotionNm(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/promotion/{promotionid}")
    public ResponseEntity deleteUser(HttpServletRequest req, @PathVariable(value="promotionid") Long promotionid) throws NoExistPromotionException {
        promotionService.deletePromotion(promotionid);
        return new ResponseEntity("delete promotion "+promotionid,HttpStatus.NO_CONTENT);
    }
}
