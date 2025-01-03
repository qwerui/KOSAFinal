package v0.project.mysite.main.member;

import org.springframework.web.multipart.MultipartFile;
import v0.project.mysite.main.member.DTO.VisitedDTO;

import java.util.List;

public interface MemberService {
    String updateProfileImage(String memberId, String image);
    List<VisitedDTO> getVisitedByMemberId(String memberId);
    void addVisited(String memberId, String storeId);
}
