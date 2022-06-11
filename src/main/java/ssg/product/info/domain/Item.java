package ssg.product.info.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @Column(name = "itemid")
    @GeneratedValue
    private Long id;

    private String itemname;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private Long itemPrice;

    private LocalDate itemDisplayStartDate;

    private LocalDate itemDisplayEndDate;


}
