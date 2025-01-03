package v0.project.mysite.main.store.DTO;

import java.util.List;
import java.util.Map;

public record StoreListRes(
        String siteId,
        String sub,
        String siteName,
        List<Object> pages
) {
}
