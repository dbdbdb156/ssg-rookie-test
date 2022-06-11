package ssg.product.info.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.Promotion;
import ssg.product.info.dto.PromotionDTO;
import ssg.product.info.exception.NoExistPromotionException;
import ssg.product.info.repository.PromotionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class PromotionService {
    private final PromotionRepository promotionRepository;

    @Transactional
    public Long createNewPromotion(PromotionDTO promotionDTO){

        Promotion promotion = Promotion.builder()
                .promotionNm(promotionDTO.getPromotionNm())
                .discountAmount(promotionDTO.getDiscountAmount())
                .discountRate(promotionDTO.getDiscountRate())
                .promotionStartDate(promotionDTO.getPromotionStartDate())
                .promotionEndDate(promotionDTO.getPromotionEndDate())
                .build();

        return promotionRepository.save(promotion).getId();
    }

    @Transactional
    public void deletePromotion(Long promotionid) throws NoExistPromotionException {
        Optional<Promotion> promotion = promotionRepository.findById(promotionid);
        if(promotion.isEmpty()) {
            throw new NoExistPromotionException("없는 상품이라 삭제가 불가능합니다.");
        }
        promotionRepository.deleteById(promotionid);
    }

}
