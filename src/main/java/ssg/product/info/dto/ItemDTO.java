package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import ssg.product.info.domain.ItemType;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ItemDTO {
    private String name;
    private ItemType type;
    private Long price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate displayStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate displayEndDate;

}
