package ssg.product.info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssg.product.info.domain.Promotion;
import ssg.product.info.dto.PromotionDTO;
import ssg.product.info.exception.NoExistPromotionException;
import ssg.product.info.repository.PromotionRepository;
import ssg.product.info.service.PromotionService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class PromotionServiceTest {
    @DisplayName(value="add new promotion")
    @Test
    void create_New_promotion(@Mock PromotionRepository promotionRepository){

        PromotionService promotionService = new PromotionService(promotionRepository);

        PromotionDTO promotionDTO =
                new PromotionDTO("2022 쓱데이",1000L,
                        null,LocalDate.of(2022,5,1),
                        LocalDate.of(2022,7,1));
        Promotion promotion = Promotion.builder()
                .promotionNm(promotionDTO.getPromotionNm())
                .discountAmount(promotionDTO.getDiscountAmount())
                .discountRate(promotionDTO.getDiscountRate())
                .promotionStartDate(promotionDTO.getPromotionStartDate())
                .promotionEndDate(promotionDTO.getPromotionEndDate())
                .build();

        when(promotionRepository.save(any())).thenReturn(promotion);

        assertNotNull(promotionService);
        promotionService.createNewPromotion(promotionDTO);
        assertEquals(promotion.getId(),promotionService.createNewPromotion(promotionDTO));
    }

    @DisplayName(value="delete promotion Exist")
    @Test
    void delete_user_exist(@Mock PromotionRepository promotionRepository) throws NoExistPromotionException {

        PromotionService promotionService = new PromotionService(promotionRepository);

        Promotion promotion = Promotion.builder()
                .id(1L)
                .promotionNm("2022 쓱데이")
                .discountAmount(1000L)
                .discountRate(null)
                .promotionStartDate(LocalDate.of(2022,5,1))
                .promotionEndDate(LocalDate.of(2022,7,1))
                .build();

        when(promotionRepository.findById(any())).thenReturn(Optional.of(promotion));

        assertNotNull(promotionService);
        promotionService.deletePromotion(1L);
        verify(promotionRepository,times(1)).deleteById(1L);

    }

    @DisplayName(value="delete promotion No Exist")
    @Test
    void delete_user_no_exist(@Mock PromotionRepository promotionRepository){

        PromotionService promotionService = new PromotionService(promotionRepository);

        Promotion promotion = Promotion.builder()
                .id(1L)
                .promotionNm("2022 쓱데이")
                .discountAmount(1000L)
                .discountRate(null)
                .promotionStartDate(LocalDate.of(2022,5,1))
                .promotionEndDate(LocalDate.of(2022,7,1))
                .build();

        when(promotionRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertNotNull(promotionService);
        assertThrows(NoExistPromotionException.class, () -> {
            promotionService.deletePromotion(1L);
        });
    }


}