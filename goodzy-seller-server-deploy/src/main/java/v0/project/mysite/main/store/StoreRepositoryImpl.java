package v0.project.mysite.main.store;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.QStore;
import v0.project.mysite.main.common.entity.Store;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public void publishStore(String sellerId, String siteId, Boolean isOpen, Long categoryId) {
        Store store = Store.builder()
                .storeId(siteId)
                .sellerId(sellerId)
                .opened(isOpen)
                .categoryId(categoryId)
                .build();

        em.merge(store);
    }
}
