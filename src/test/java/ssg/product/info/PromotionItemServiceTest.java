package ssg.product.info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssg.product.info.domain.PromotionItem;
import ssg.product.info.dto.PromotionItemDTO;
import ssg.product.info.exception.NoExistPromotionItemException;
import ssg.product.info.repository.PromotionItemRepository;
import ssg.product.info.service.PromotionItemService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class PromotionItemServiceTest {
    @DisplayName(value="add new promotionItem")
    @Test
    void create_New_PromotionItem(@Mock PromotionItemRepository promotionItemRepository){

        PromotionItemService promotionItemService = new PromotionItemService(promotionItemRepository);
        PromotionItemDTO promotionItemDTO = new PromotionItemDTO(1L,1L);

        PromotionItem promotionItem = PromotionItem.builder()
                .id(1L)
                .promotionId(promotionItemDTO.getPromotionid())
                .itemId(promotionItemDTO.getItemid())
                .build();

        when(promotionItemRepository.save(any())).thenReturn(promotionItem);

        assertNotNull(promotionItemService);
        promotionItemService.createNewPromotionItem(promotionItemDTO);
        assertEquals(promotionItem.getId(),promotionItemService.createNewPromotionItem(promotionItemDTO));
    }

    @DisplayName(value="delete promotionItem Exist")
    @Test
    void delete_PromotionItem_exist(@Mock PromotionItemRepository promotionItemRepository) throws NoExistPromotionItemException {

        PromotionItemService promotionItemService = new PromotionItemService(promotionItemRepository);
        PromotionItem promotionItem = new PromotionItem(1L,1L,1L);

        when(promotionItemRepository.findById(any())).thenReturn(Optional.of(promotionItem));

        assertNotNull(promotionItemService);
        promotionItemService.deletePromotionItem(1L);
        verify(promotionItemRepository,times(1)).deleteById(1L);

    }

    @DisplayName(value="delete promotionItem No Exist")
    @Test
    void delete_PromotionItem_no_exist(@Mock PromotionItemRepository promotionItemRepository) {

        PromotionItemService promotionItemService = new PromotionItemService(promotionItemRepository);
        PromotionItem promotionItem = new PromotionItem(1L,1L,1L);

        when(promotionItemRepository.findById(any())).thenReturn(Optional.empty());

        assertNotNull(promotionItemService);
        assertThrows(NoExistPromotionItemException.class, () -> {
            promotionItemService.deletePromotionItem(1L);
        });
    }
}