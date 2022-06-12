package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.Promotion;

@AllArgsConstructor
@Getter
@Builder
public class ItemPromotionInfoDTO {
    private Item item;
    private Long discountPrice;
    private Promotion promotion;
}
