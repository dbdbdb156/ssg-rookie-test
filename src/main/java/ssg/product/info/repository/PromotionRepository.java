package ssg.product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssg.product.info.domain.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Long> {
}
