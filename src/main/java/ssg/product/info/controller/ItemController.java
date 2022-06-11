package ssg.product.info.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.product.info.domain.ItemType;
import ssg.product.info.dto.ItemDTO;
import ssg.product.info.exception.NoExistItemException;
import ssg.product.info.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/api/item/name/{name}/type/{type}/price/{price}/startdate/{startdate}/enddate/{enddate}")
    public ResponseEntity inputUser(HttpServletRequest req,
                                    @PathVariable(value="name") String username,
                                    @PathVariable(value="type") ItemType itemType,
                                    @PathVariable(value="price") Long price,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                                    @PathVariable(value="startdate") LocalDate startdate,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                                    @PathVariable(value="enddate") LocalDate enddate
    ){
        ItemDTO itemDTO = new ItemDTO(username,itemType,price,startdate,enddate);
        itemService.createNewItem(itemDTO);
        return ResponseEntity.ok("make item "+username);
    }

    @DeleteMapping("/api/item/id/{id}")
    public ResponseEntity deleteUser(HttpServletRequest req, @PathVariable(value="id") Long id) throws NoExistItemException {
        itemService.deleteItem(id);
        return ResponseEntity.ok("delete item "+id);
    }

}
