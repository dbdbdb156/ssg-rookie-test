package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PromotionDTO {
    private String promotionNm;
    private Long discountAmount;
    private Double discountRate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate promotionStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate promotionEndDate;


}
