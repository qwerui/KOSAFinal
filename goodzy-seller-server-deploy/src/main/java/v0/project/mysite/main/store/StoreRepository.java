package v0.project.mysite.main.store;

import v0.project.mysite.main.common.entity.Store;

import java.util.List;

public interface StoreRepository {
    void publishStore(String sellerId, String siteId, Boolean isOpen, Long categoryId);
}
