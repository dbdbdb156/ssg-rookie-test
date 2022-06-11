package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.service.ItemService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/api/item")
    public ResponseEntity inputUser(HttpServletRequest req,ItemDTO itemDTO
    ){
        itemService.createNewItem(itemDTO);
        return ResponseEntity.ok("make item "+itemDTO.getItemname());
    }

    @DeleteMapping("/api/item/{id}")
    public ResponseEntity deleteItem(HttpServletRequest req, @PathVariable(value="id") Long id) throws NoExistItemException {
        itemService.deleteItem(id);
        return ResponseEntity.ok("delete item "+id);
    }

}
