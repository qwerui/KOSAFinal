package v0.project.mysite.main.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import v0.project.mysite.main.member.DTO.UpdateProfileImageReq;
import v0.project.mysite.main.member.DTO.VisitedDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/image")
    public ResponseEntity<?> updateProfileImage(@RequestBody UpdateProfileImageReq req) {
        String resultPath = memberService.updateProfileImage(req.memberId(), req.image());

        return ResponseEntity.ok().body(resultPath);
    }

    @GetMapping("/mypage/visited")
    public List<VisitedDTO> getVisitedByMemeberId(@RequestParam String memberId) {
        return memberService.getVisitedByMemberId(memberId);
    }

    @PostMapping("/mypage/visited")
    public ResponseEntity<?> updateVisited(@RequestParam String memberId, @RequestParam String storeId) {

        memberService.addVisited(memberId, storeId);

        return ResponseEntity.ok().build();
    }
}
