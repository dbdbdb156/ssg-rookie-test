package ssg.product.info.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.Promotion;
import ssg.product.info.dto.ItemPromotionInfoDTO;
import ssg.product.info.dto.PromotionDTO;
import ssg.product.info.exception.NoExistPromotionException;
import ssg.product.info.repository.PromotionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class PromotionService {
    private final PromotionRepository promotionRepository;

    @Transactional
    public Promotion createNewPromotion(PromotionDTO promotionDTO){

        Promotion promotion = Promotion.builder()
                .promotionNm(promotionDTO.getName())
                .discountAmount(promotionDTO.getDiscountAmount())
                .discountRate(promotionDTO.getDiscountRate())
                .promotionStartDate(promotionDTO.getStartDate())
                .promotionEndDate(promotionDTO.getEndDate())
                .build();

        return promotionRepository.save(promotion);
    }

    @Transactional
    public void deletePromotion(Long promotionid) throws NoExistPromotionException {
        Optional<Promotion> promotion = promotionRepository.findById(promotionid);
        if(promotion.isEmpty()) {
            throw new NoExistPromotionException("없는 상품이라 삭제가 불가능합니다.");
        }
        promotionRepository.deleteById(promotionid);
    }

    @Transactional(readOnly = true)
    public List<Promotion> promotionsForItems(List<Long> promotionsId){
        ArrayList<Promotion> list = new ArrayList<>();
        for(Long promotionid : promotionsId){
            Optional<Promotion> promotion = promotionRepository.findById(promotionid);
            if(promotion.isPresent()) list.add(promotion.get());
        }
        LocalDate now = LocalDate.now();
        return list.stream().filter(promotion-> promotion.getPromotionStartDate().compareTo(now)<=0
                && promotion.getPromotionEndDate().compareTo(now) >= 0
                && ( (promotion.getDiscountAmount()==null && promotion.getDiscountRate() != null)  ||
                        (promotion.getDiscountAmount()!=null) && promotion.getDiscountRate() == null ))
                .collect(Collectors.toList());
    }

    public ItemPromotionInfoDTO choiceBestPromotion(Item item, List<Promotion> promotions) throws NoExistPromotionException{
        Long price = item.getItemPrice();
        Long minPrice = Long.MAX_VALUE;
        int idx = -1;
        for(int i=0;i<promotions.size();++i){
            Promotion promotion = promotions.get(i);
            if(promotion.getDiscountAmount()==null){
                Long discountPrice = promotion.discountPriceRate(price);
                System.out.println(discountPrice);
                if(discountPrice.compareTo(0L)>0 && discountPrice.compareTo(minPrice)<0){
                    idx = i;
                    minPrice = discountPrice;
                }
            }
            else{
                Long discountPrice = promotion.discountPriceAmount(price);
                System.out.println(discountPrice);
                if(discountPrice.compareTo(0L)>0 && discountPrice.compareTo(minPrice)<0){
                    idx = i;
                    minPrice = discountPrice;
                }
            }
        }
        if(idx == -1){
            throw new NoExistPromotionException("프로모션이 존재 하지 않습니다.");
        }

        return ItemPromotionInfoDTO.builder()
                .promotion(promotions.get(idx))
                .item(item)
                .discountPrice(minPrice)
                .build();

    }



}
