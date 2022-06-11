package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok("make promotion "+promotionDTO.getPromotionNm());
    }

    @DeleteMapping("/api/promotion/{promotionid}")
    public ResponseEntity deleteUser(HttpServletRequest req, @PathVariable(value="promotionid") Long promotionid) throws NoExistPromotionException {
        promotionService.deletePromotion(promotionid);
        return ResponseEntity.ok("delete promotion "+promotionid);
    }
}
