package ssg.product.info.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.User;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.exception.NoExistUserException;
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
        // id가 중복이 될 경우는 키를 자동 생성하기에 이미 있는 유저가 있을 경우는 없음.
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
