package ssg.product.info.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.ItemType;
import ssg.product.info.domain.Promotion;
import ssg.product.info.exception.NoExistPromotionException;
import ssg.product.info.repository.ItemRepository;
import ssg.product.info.repository.PromotionRepository;
import ssg.product.info.service.PromotionService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class PromotionItemTest {

    /* 테스트 가정
    1. 상품의 아이디가 존재하지 않음 : ItemServiceTest 에서 테스트 함.
    2. 프로모션이 하나 존재할때, 기간이 포함되지 않아 empty.
    3. 프로모션이 하나 존재하고 가격 결과가 0원 이하일때, promotion empty
    4. 정상 작동
     */

    @DisplayName(value = "not in promotion date ")
    @Test
    void not_in_promotion_date(@Mock ItemRepository itemRepository, @Mock PromotionRepository promotionRepository){

        PromotionService promotionService = new PromotionService(promotionRepository);
        Promotion promotion = new Promotion(1L,"상품 할인 데이",1000L,null,
                LocalDate.of(2021,1,1),
                LocalDate.of(2021,12,31));

        when(promotionRepository.findById(any())).thenReturn(Optional.of(promotion));

        assertTrue(promotionService.promotionsForItems(Arrays.asList(1L)).isEmpty());
    }

    @DisplayName(value = " discount price is Minus")
    @Test
    void discount_Price_Minus( @Mock PromotionRepository promotionRepository){
        PromotionService promotionService = new PromotionService(promotionRepository);
        Promotion promotion = new Promotion(1L,"상품 할인 데이",10000L,null,
                LocalDate.of(2021,1,1),
                LocalDate.of(2300,12,31));
        Item item = new Item(1L,"과자",ItemType.일반,500L,
                LocalDate.of(2021,1,1),
                LocalDate.of(2300,12,31));


        assertThrows(NoExistPromotionException.class, () -> {
            promotionService.choiceBestPromotion(item ,Arrays.asList(promotion));
        });

    }

    @DisplayName(value = " apply promotion to item")
    @Test
    void promotion_To_Item(@Mock PromotionRepository promotionRepository) throws NoExistPromotionException {

        PromotionService promotionService = new PromotionService(promotionRepository);
        Item item = new Item(1L,"과자",ItemType.일반,100000L,
                LocalDate.of(2021,1,1),
                LocalDate.of(2300,12,31));
        List<Promotion> promotions = Arrays.asList(
                new Promotion(1L,"상품 할인 데이",10000L,null,
                        LocalDate.of(2021,1,1),
                        LocalDate.of(2300,12,31)),
                new Promotion(2L,"상품 할인 데이",30000L,null,
                        LocalDate.of(2021,1,1),
                        LocalDate.of(2300,12,31)),
                new Promotion(3L,"상품 할인 데이",null,0.5,
                        LocalDate.of(2021,1,1),
                        LocalDate.of(2300,12,31))
        );

        assertEquals(50000L,promotionService.choiceBestPromotion(item, promotions).getDiscountPrice());
    }


}
