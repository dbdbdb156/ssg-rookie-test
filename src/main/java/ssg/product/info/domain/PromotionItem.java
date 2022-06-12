package ssg.product.info.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class PromotionItem {
    @Id
    @Column(name = "promotionitemid")
    @GeneratedValue
    private Long id;

    private Long promotionId;

    private Long itemId;
}
