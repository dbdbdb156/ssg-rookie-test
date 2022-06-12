package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity inputUser(HttpServletRequest req,UserDTO userDTO){
        userService.createNewUser(userDTO);
        return new ResponseEntity("make user "+userDTO.getUsername(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity deleteUser(HttpServletRequest req, @PathVariable(value="id") Long id) throws NoExistUserException {
        userService.deleteUser(id);
        return new ResponseEntity("delete user "+id,HttpStatus.NO_CONTENT);
    }

}
