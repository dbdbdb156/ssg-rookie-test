package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.domain.UserStat;
import ssg.product.info.domain.UserType;
import ssg.product.info.dto.UserDTO;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.service.UserService;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/name/{name}/type/{type}/stat/{stat}")
    public ResponseEntity inputUser(HttpServletRequest req,
                                    @PathVariable(value="name") String username,
                                    @PathVariable(value="type") UserType userType,
                                    @PathVariable(value="stat") UserStat userStat
    ){
        userService.createNewUser(new UserDTO(username,userType,userStat));
        return ResponseEntity.ok("make user "+username);
    }

    @DeleteMapping("/api/user/id/{id}")
    public ResponseEntity deleteUser(HttpServletRequest req, @PathVariable(value="id") Long id) throws NoExistUserException {
        userService.deleteUser(id);
        return ResponseEntity.ok("delete user "+id);
    }

}
