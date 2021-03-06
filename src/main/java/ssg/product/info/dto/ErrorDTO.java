package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorDTO {
    private Long code;
    private HttpStatus status;
    private String message;
}
