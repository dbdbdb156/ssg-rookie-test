package ssg.product.info.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.*;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.repository.ItemRepository;

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
    public Item createNewItem(ItemDTO itemDTO){
        Item item = Item.builder()
                .itemname(itemDTO.getName())
                .itemPrice(itemDTO.getPrice())
                .itemType(itemDTO.getType())
                .itemDisplayStartDate(itemDTO.getDisplayStartDate())
                .itemDisplayEndDate(itemDTO.getDisplayEndDate())
                .build();
        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long itemid) throws NoExistItemException {
        Optional<Item> item = itemRepository.findById(itemid);
        isItemExist(item);
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

    @Transactional(readOnly = true)
    public Optional<Item> getItem(Long itemid){
        return itemRepository.findById(itemid);
    }

    public void isItemExist(Optional<Item> item) throws NoExistItemException {
        if(item.isEmpty()){
            throw new NoExistItemException("유저가 존재 하지 않습니다.");
        }
    }

}
