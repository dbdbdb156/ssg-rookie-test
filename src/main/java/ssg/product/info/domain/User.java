package ssg.product.info.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column(name = "userid")
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStat userStat;

}
