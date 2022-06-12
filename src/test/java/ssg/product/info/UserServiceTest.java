package ssg.product.info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssg.product.info.domain.User;
import ssg.product.info.domain.UserStat;
import ssg.product.info.domain.UserType;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.repository.UserRepository;
import ssg.product.info.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @DisplayName(value="add new user")
    @Test
    void create_New_User(@Mock UserRepository userRepository){

        UserService userService = new UserService(userRepository);
        UserDTO userDTO = new UserDTO("홍길동",UserType.일반,UserStat.정상);
        User user = User.builder()
                .id(1L)
                .username(userDTO.getUsername())
                .userType(userDTO.getUserType())
                .userStat(userDTO.getUserStat())
                .build();

        when(userRepository.save(any())).thenReturn(user);

        assertNotNull(userService);
        userService.createNewUser(userDTO);
        assertEquals(user.getId(),userService.createNewUser(userDTO));
    }

    @DisplayName(value="delete user Exist")
    @Test
    void delete_user_exist(@Mock UserRepository userRepository) throws NoExistUserException {

        UserService userService = new UserService(userRepository);
        User user = User.builder()
                .id(1L)
                .username("홍길동")
                .userType(UserType.일반)
                .userStat(UserStat.정상)
                .build();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        assertNotNull(userService);
        userService.deleteUser(1L);
        verify(userRepository,times(1)).deleteById(1L);

    }

    @DisplayName(value="delete user No Exist")
    @Test
    void delete_user_no_exist(@Mock UserRepository userRepository){

        UserService userService = new UserService(userRepository);
        User user = User.builder()
                .id(1L)
                .username("홍길동")
                .userType(UserType.일반)
                .userStat(UserStat.정상)
                .build();

        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertNotNull(userService);
        assertThrows(NoExistUserException.class, () -> {
            userService.deleteUser(1L);
        });
    }



}
