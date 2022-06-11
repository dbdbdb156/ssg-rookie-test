package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ssg.product.info.domain.UserStat;
import ssg.product.info.domain.UserType;


@AllArgsConstructor
@Getter
public class UserDTO {
    private String username;
    private UserType userType;
    private UserStat userStat;

}
