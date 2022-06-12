package ssg.product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ssg.product.info.domain.PromotionItem;

import java.util.List;

@Repository
public interface PromotionItemRepository extends JpaRepository<PromotionItem,Long> {
    @Query("select pi.promotionId from PromotionItem pi where pi.itemId = :itemid")
    List<Long> findAllByItemId(Long itemid);
}
