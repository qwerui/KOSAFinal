package v0.project.mysite.main.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.*;
import v0.project.mysite.main.member.DTO.VisitedDTO;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VisitedStoreRepositoryImpl implements VisitedStoreRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public List<VisitedDTO> getVisitedByMemberId(String memberId) {

        QVisitedStore visited = QVisitedStore.visitedStore;
        QStore store = QStore.store;
        QCategory category = QCategory.category;

        return queryFactory
                .select(Projections.constructor(VisitedDTO.class,
                        visited.visitedStoreId.storeId,
                        store.name,
                        store.storeImage))
                .from(visited)
                .innerJoin(store).on(visited.visitedStoreId.storeId.eq(store.storeId))
                .where(visited.visitedStoreId.memberId.eq(memberId))
                .orderBy(visited.visitedTime.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public void addVisited(String memberId, String storeId) {

        var visitedId = VisitedStoreId.builder()
                .storeId(storeId)
                .memberId(memberId)
                .build();

        var visited = VisitedStore.builder()
                .visitedStoreId(visitedId)
                .visitedTime(LocalDateTime.now())
                .build();

        em.persist(visited);
    }
}
