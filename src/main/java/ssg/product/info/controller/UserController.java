package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssg.product.info.dto.RequestIdDTO;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity inputUser(UserDTO userDTO){
        userService.createNewUser(userDTO);
        return new ResponseEntity("make user "+userDTO.getUsername(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/user")
    public ResponseEntity deleteUser(RequestIdDTO useridDTO) throws NoExistUserException {
        userService.deleteUser(useridDTO.getId());
        return new ResponseEntity("delete user "+useridDTO.getId(),HttpStatus.NO_CONTENT);
    }


}
