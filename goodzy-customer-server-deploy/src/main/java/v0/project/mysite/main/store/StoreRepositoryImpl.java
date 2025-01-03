package v0.project.mysite.main.store;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.QStore;
import v0.project.mysite.main.common.entity.Store;
import v0.project.mysite.main.store.DTO.StoreListRes;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository{

    private final JPAQueryFactory queryFactory;
}
