package ssg.product.info.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Item {
    @Id
    @Column(name = "itemid")
    private Long id;

    private String itemname;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private Long itemPrice;

    private LocalDateTime itemDisplayStartDate;

    private LocalDateTime itemDisplayEndDate;


}
