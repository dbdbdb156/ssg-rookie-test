package ssg.product.info.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionItem {
    @Id
    @Column(name = "promotionItemId")
    @GeneratedValue
    private Long id;

    private Long promotionId;

    private Long itemId;
}
