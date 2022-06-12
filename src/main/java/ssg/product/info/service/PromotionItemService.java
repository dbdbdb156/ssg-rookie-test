package ssg.product.info.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.PromotionItem;
import ssg.product.info.dto.PromotionItemDTO;
import ssg.product.info.exception.NoExistPromotionItemException;
import ssg.product.info.repository.PromotionItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class PromotionItemService {
    private final PromotionItemRepository promotionItemRepository;

    @Transactional
    public Long createNewPromotionItem(PromotionItemDTO promotionItemDTO){

        PromotionItem promotionItem = PromotionItem.builder()
                .promotionId(promotionItemDTO.getPromotionid())
                .itemId(promotionItemDTO.getItemid())
                .build();

        return promotionItemRepository.save(promotionItem).getId();
    }

    @Transactional
    public void deletePromotionItem(Long promotionItemid) throws NoExistPromotionItemException {
        Optional<PromotionItem> promotion = promotionItemRepository.findById(promotionItemid);
        if(promotion.isEmpty()) {
            throw new NoExistPromotionItemException("없는 상품이라 삭제가 불가능합니다.");
        }
        promotionItemRepository.deleteById(promotionItemid);
    }

    @Transactional(readOnly = true)
    public List<Long> promotionsForItem(Long itemid){
        return promotionItemRepository.findAllByItemId(itemid);
    }

}
