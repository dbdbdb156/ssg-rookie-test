package ssg.product.info.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.product.info.domain.User;
import ssg.product.info.domain.UserStat;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.exception.WithdrawalException;
import ssg.product.info.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createNewUser(UserDTO userDTO){
        User user = User.builder()
                .username(userDTO.getName())
                .userType(userDTO.getType())
                .userStat(userDTO.getStat())
                .build();
        // id가 중복이 될 경우는 키를 자동 생성하기에 이미 있는 유저가 있을 경우는 없음.
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userid) throws NoExistUserException {
        Optional<User> user = userRepository.findById(userid);
        isUserExist(user);
        userRepository.deleteById(userid);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(Long userid){
        return userRepository.findById(userid);
    }

    public void isUserExist(Optional<User> user) throws NoExistUserException {
        if(user.isEmpty()){
            throw new NoExistUserException("유저가 존재 하지 않습니다.");
        }
    }

    public void isUserWithdrawal(UserStat userStat) throws WithdrawalException {
        if(userStat.equals(UserStat.탈퇴)){
            throw new WithdrawalException("유저가 탈퇴하였습니다");
        }
    }



}

