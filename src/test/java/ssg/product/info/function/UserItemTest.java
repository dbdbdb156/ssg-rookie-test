package ssg.product.info.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssg.product.info.domain.*;
import ssg.product.info.exception.WithdrawalException;
import ssg.product.info.repository.ItemRepository;
import ssg.product.info.repository.UserRepository;
import ssg.product.info.service.ItemService;
import ssg.product.info.service.UserService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class UserItemTest {
    /*
    1. 사용자의 아이디가 존재하지 않을때 : userServiceTest 에서 테스트 함.
    2. 사용자의 아이디는 존재했지만 탈퇴함
    3. 조회가 성공했을때
      1) 일반 회원이 조회했을때
      2) 기업 회원이 조회했을 때
     */

    @DisplayName("withdrawal user exist")
    @Test
    void withdrawal_user_exist(@Mock UserRepository userRepository) throws WithdrawalException {
        UserService userService = new UserService(userRepository);

        User user = User.builder()
                .id(1L)
                .username("홍길동")
                .userType(UserType.일반)
                .userStat(UserStat.탈퇴)
                .build();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        assertNotNull(userService);
        assertThrows(WithdrawalException.class, () -> {
            userService.isUserWithdrawal(userService.getUser(1L).get().getUserStat());
        });
    }

    @DisplayName(value = "items for normal user")
    @Test
    void items_for_normal_user(@Mock UserRepository userRepository,@Mock ItemRepository itemRepository){
        UserService userService = new UserService(userRepository);
        ItemService itemService = new ItemService(itemRepository);

        User user = new User(1L,"홍길동",UserType.일반,UserStat.정상);
        List<Item> items = Arrays.asList(
                new Item(1L,"노브랜드 버터링", ItemType.일반,20000L, LocalDate.of(2022,1,1),LocalDate.of(2023,1,1)),
                new Item(2L,"매일 아침 우유", ItemType.일반,1000L, LocalDate.of(2021,1,1),LocalDate.of(2023,5,5)),
                new Item(3L,"나이키 운동화", ItemType.기업회원상품,40000L, LocalDate.of(2020,1,1),LocalDate.of(2023,12,31)),
                new Item(4L,"스타벅스 써머 텀블러", ItemType.일반,15000L, LocalDate.of(2021,1,1),LocalDate.of(2023,8,1)),
                new Item(5L,"크리스마스 케이크", ItemType.일반,30000L, LocalDate.of(2022,12,24),LocalDate.of(2023,12,31))
        );

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(itemRepository.findAll()).thenReturn(items);

        List<Item> actualItems = itemService.itemListForUser(userService.getUser(1L).get().getUserType());

        assertThat(actualItems,containsInAnyOrder(
                hasProperty("itemname", is("노브랜드 버터링")),
                hasProperty("itemname", is("매일 아침 우유")),
                hasProperty("itemname", is("스타벅스 써머 텀블러"))
                ));

    }

    @DisplayName(value = "items for company user")
    @Test
    void items_for_company_user(@Mock UserRepository userRepository,@Mock ItemRepository itemRepository){
        UserService userService = new UserService(userRepository);
        ItemService itemService = new ItemService(itemRepository);

        User user = new User(1L,"홍길동",UserType.기업회원,UserStat.정상);
        List<Item> items = Arrays.asList(
                new Item(1L,"노브랜드 버터링", ItemType.일반,20000L, LocalDate.of(2022,1,1),LocalDate.of(2023,1,1)),
                new Item(2L,"매일 아침 우유", ItemType.일반,1000L, LocalDate.of(2021,1,1),LocalDate.of(2023,5,5)),
                new Item(3L,"나이키 운동화", ItemType.기업회원상품,40000L, LocalDate.of(2020,1,1),LocalDate.of(2023,12,31)),
                new Item(4L,"스타벅스 써머 텀블러", ItemType.일반,15000L, LocalDate.of(2021,1,1),LocalDate.of(2023,8,1)),
                new Item(5L,"크리스마스 케이크", ItemType.일반,30000L, LocalDate.of(2022,12,24),LocalDate.of(2023,12,31))
        );

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(itemRepository.findAll()).thenReturn(items);

        List<Item> actualItems = itemService.itemListForUser(userService.getUser(1L).get().getUserType());

        assertThat(actualItems,containsInAnyOrder(
                hasProperty("itemname", is("노브랜드 버터링")),
                hasProperty("itemname", is("매일 아침 우유")),
                hasProperty("itemname", is("나이키 운동화")),
                hasProperty("itemname", is("스타벅스 써머 텀블러"))
        ));

    }

}
