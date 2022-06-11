package ssg.product.info.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @Id
    @Column(name = "promotionid")
    @GeneratedValue
    private Long id;

    private String promotionNm;

    @Nullable
    private Long discountAmount;
    @Nullable
    private Double discountRate;

    private LocalDate promotionStartDate;

    private LocalDate promotionEndDate;



}
