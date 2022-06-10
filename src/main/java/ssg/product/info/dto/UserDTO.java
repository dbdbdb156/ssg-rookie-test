package ssg.product.info.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ssg.product.info.domain.User;
import ssg.product.info.domain.UserStat;
import ssg.product.info.domain.UserType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
public class UserDTO {
    private String username;
    private UserType userType;
    private UserStat userStat;

}
