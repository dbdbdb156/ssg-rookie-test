package ssg.product.info.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Promotion {

    @Id
    @Column(name = "promotionid")
    private Long id;

    private String promotionNm;

    private Long discountAmount;

    private Long discountRate;

    private LocalDateTime promotionStartDate;

    private LocalDateTime promotionEndDate;



}
