package v0.project.mysite.main.member;

import v0.project.mysite.main.member.DTO.VisitedDTO;

import java.util.List;

public interface VisitedStoreRepository {
    List<VisitedDTO> getVisitedByMemberId(String memberId);
    void addVisited(String memberId, String storeId);
}
