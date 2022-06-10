package ssg.product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssg.product.info.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
