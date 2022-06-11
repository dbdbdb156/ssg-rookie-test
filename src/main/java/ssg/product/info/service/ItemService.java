package ssg.product.info.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.Item;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.repository.ItemRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
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

}
