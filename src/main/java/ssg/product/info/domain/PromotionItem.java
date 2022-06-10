package ssg.product.info.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PromotionItem {
    @Id
    @Column(name = "promotionitemid")
    @GeneratedValue
    private Long id;

    private Long promotionid;

    private Long itemid;
}
