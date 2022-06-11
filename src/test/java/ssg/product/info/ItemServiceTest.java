package ssg.product.info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssg.product.info.domain.*;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.repository.ItemRepository;
import ssg.product.info.repository.UserRepository;
import ssg.product.info.service.ItemService;
import ssg.product.info.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @DisplayName(value="add new item")
    @Test
    void create_New_item(@Mock ItemRepository itemRepository){

        ItemService itemService = new ItemService(itemRepository);
        ItemDTO itemDTO = new ItemDTO("노브랜드 버터링", ItemType.일반,20000L,
                LocalDate.of(2022,1,1),LocalDate.of(2023,1,1));

        Item item = Item.builder()
                .id(1L)
                .itemname(itemDTO.getItemname())
                .itemType(itemDTO.getItemType())
                .itemPrice(itemDTO.getItemPrice())
                .itemDisplayStartDate(itemDTO.getItemDisplayStartDate())
                .itemDisplayEndDate(itemDTO.getItemDisplayEndDate())
                .build();

        when(itemRepository.save(any())).thenReturn(item);

        assertNotNull(itemService);
        itemService.createNewItem(itemDTO);
        assertEquals(item.getId(),itemService.createNewItem(itemDTO));
    }

    @DisplayName(value="delete item Exist")
    @Test
    void delete_user_exist(@Mock ItemRepository itemRepository) throws NoExistItemException {

        ItemService itemService = new ItemService(itemRepository);
        Item item = Item.builder()
                .id(1L)
                .itemname("노브랜드 버터링")
                .itemType(ItemType.일반)
                .itemPrice(20000L)
                .itemDisplayStartDate(LocalDate.of(2022,1,1))
                .itemDisplayEndDate(LocalDate.of(2023,1,1))
                .build();

        when(itemRepository.findById(any())).thenReturn(Optional.of(item));

        assertNotNull(itemService);
        itemService.deleteItem(1L);
        verify(itemRepository,times(1)).deleteById(1L);

    }

    @DisplayName(value="delete item No Exist")
    @Test
    void delete_user_no_exist(@Mock ItemRepository itemRepository) throws NoExistItemException {

        ItemService itemService = new ItemService(itemRepository);
        Item item = Item.builder()
                .id(1L)
                .itemname("노브랜드 버터링")
                .itemType(ItemType.일반)
                .itemPrice(20000L)
                .itemDisplayStartDate(LocalDate.of(2022,1,1))
                .itemDisplayEndDate(LocalDate.of(2023,1,1))
                .build();

        when(itemRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertNotNull(itemService);
        assertThrows(NoExistItemException.class, () -> {
            itemService.deleteItem(1L);
        });
    }

}