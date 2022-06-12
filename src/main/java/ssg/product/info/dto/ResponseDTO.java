package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO {
    private boolean success;
    private Object response;
    private Object error;
}
