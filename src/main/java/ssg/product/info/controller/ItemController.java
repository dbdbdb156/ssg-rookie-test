package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssg.product.info.domain.Item;
import ssg.product.info.domain.User;
import ssg.product.info.dto.*;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.exception.NoExistUserException;
import ssg.product.info.exception.WithdrawalException;
import ssg.product.info.service.ItemService;
import ssg.product.info.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class ItemController {
    private final ItemService itemService;
    private final UserService userService;

    // 201 생성 성공
    @PostMapping("/item")
    public ResponseEntity inputItem(ItemDTO itemDTO){
        Item item = itemService.createNewItem(itemDTO);
        ResponseDTO body = new ResponseDTO(true, new ContentDTO(201L,HttpStatus.CREATED,"make item",item), null);
        return new ResponseEntity(body, HttpStatus.CREATED);
    }

    // 204 삭제 성공
    @DeleteMapping("/item")
    public ResponseEntity deleteItem(RequestIdDTO itemidDTO) throws NoExistItemException {
        itemService.deleteItem(itemidDTO.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // 200 성공
    @GetMapping("/items")
    public ResponseEntity itemListforUser(RequestIdDTO userIdDTO) throws NoExistUserException, WithdrawalException {

        Optional<User> user = userService.getUser(userIdDTO.getId());

        userService.isUserExist(user);
        userService.isUserWithdrawal(user.get().getUserStat());

        List<Item> list = itemService.itemListForUser(user.get().getUserType());
        ResponseDTO body = new ResponseDTO(true, new ContentDTO(200L,HttpStatus.OK,"items for user",list), null);
        return new ResponseEntity(body,HttpStatus.OK);
    }

}
