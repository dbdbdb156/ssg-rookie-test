package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssg.product.info.domain.User;
import ssg.product.info.dto.ContentDTO;
import ssg.product.info.dto.RequestIdDTO;
import ssg.product.info.dto.ResponseDTO;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity inputUser(UserDTO userDTO){
        User user = userService.createNewUser(userDTO);
        ResponseDTO body = new ResponseDTO(true, new ContentDTO(201L,HttpStatus.CREATED,"make user",user), null);
        return new ResponseEntity(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUser(RequestIdDTO useridDTO) throws NoExistUserException {
        userService.deleteUser(useridDTO.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
