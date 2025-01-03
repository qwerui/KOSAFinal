package v0.project.mysite.main.member.DTO;

import org.springframework.web.multipart.MultipartFile;

public record UpdateProfileImageReq(
        String memberId,
        String image
) {
}
