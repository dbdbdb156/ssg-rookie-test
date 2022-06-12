package ssg.product.info.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.ItemType;
import ssg.product.info.domain.UserStat;
import ssg.product.info.domain.UserType;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.repository.ItemRepository;
import ssg.product.info.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long createNewItem(ItemDTO itemDTO){
        Item item = Item.builder()
                .itemname(itemDTO.getItemname())
                .itemPrice(itemDTO.getItemPrice())
                .itemType(itemDTO.getItemType())
                .itemDisplayStartDate(itemDTO.getItemDisplayStartDate())
                .itemDisplayEndDate(itemDTO.getItemDisplayEndDate())
                .build();
        return itemRepository.save(item).getId();
    }

    @Transactional
    public void deleteItem(Long itemid) throws NoExistItemException {
        Optional<Item> item = itemRepository.findById(itemid);
        if(item.isEmpty()) {
            throw new NoExistItemException("없는 상품이라 삭제가 불가능합니다.");
        }
        itemRepository.deleteById(itemid);
    }

    @Transactional(readOnly = true)
    public List<Item> itemListForUser(UserType userType){

        LocalDate now = LocalDate.now();
        List<Item> list = itemRepository.findAll();
        if(userType.equals(UserType.일반)){
            list = list.stream()
                    .filter(item-> item.getItemType().equals(ItemType.일반))
                    .collect(Collectors.toList());
        }
        return list.stream()
                .filter(item-> item.getItemDisplayStartDate().compareTo(now)<=0
                && item.getItemDisplayEndDate().compareTo(now) >= 0 )
                .collect(Collectors.toList());
    }


}
