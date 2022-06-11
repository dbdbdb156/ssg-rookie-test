package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ssg.product.info.domain.ItemType;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ItemDTO {
    private String itemname;
    private ItemType itemType;
    private Long itemPrice;
    private LocalDate itemDisplayStartDate;
    private LocalDate itemDisplayEndDate;

}
