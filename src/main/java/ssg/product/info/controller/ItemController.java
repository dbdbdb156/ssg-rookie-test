package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.User;
import ssg.product.info.domain.UserStat;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.exception.WithdrawalException;
import ssg.product.info.service.ItemService;
import ssg.product.info.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final UserService userService;

    // 201 생성 성공
    @PostMapping("/api/item")
    public ResponseEntity inputUser(HttpServletRequest req,ItemDTO itemDTO
    ){
        itemService.createNewItem(itemDTO);
        return new ResponseEntity("make item "+itemDTO.getItemname(), HttpStatus.CREATED);
    }

    // 204 삭제 성공
    @DeleteMapping("/api/item/{id}")
    public ResponseEntity deleteItem(HttpServletRequest req, @PathVariable(value="id") Long id) throws NoExistItemException {
        itemService.deleteItem(id);
        return new ResponseEntity("delete item "+id,HttpStatus.NO_CONTENT);
    }

    // 200 성공
    @GetMapping("/api/items")
    public ResponseEntity itemListforUser(@RequestParam(value="userid") Long userid) throws NoExistUserException, WithdrawalException {

        Optional<User> user = userService.getUser(userid);

        userService.isUserExist(user);
        userService.isUserWithdrawal(user.get().getUserStat());

        List<Item> list = itemService.itemListForUser(user.get().getUserType());

        return new ResponseEntity(list,HttpStatus.OK);
    }

}
