package v0.project.mysite.main.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import v0.project.mysite.main.common.entity.ArticleImage;
import v0.project.mysite.main.common.entity.ArticleImageId;
import v0.project.mysite.main.member.DTO.VisitedDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log
public class MemberServiceImpl implements MemberService{

    private final VisitedStoreRepository visitedStoreRepository;

    @Value("${image.profile}")
    private String profilePath;

    @Override
    public String updateProfileImage(String memberId, String image) {

        String fileExtension = ""; // 파일 확장자를 저장할 변수

        // Base64 문자열의 MIME 타입을 기반으로 파일 확장자를 결정
        if (image.startsWith("data:image/png;")) {
            fileExtension = ".png";
        } else if (image.startsWith("data:image/jpeg;")) {
            fileExtension = ".jpg";
        } else if (image.startsWith("data:image/gif;")) {
            fileExtension = ".gif";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "지원하지 않는 이미지 형식입니다.");
        }

        // 파일명 생성
        String filename = memberId+"_"+UUID.randomUUID().toString()+fileExtension;

        // base64 디코딩
        String base64Data = image.split(",")[1]; // prefix 제거
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        // 로컬 파일 시스템에 저장
        try {
            Path directoryPath = Paths.get(profilePath);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); // 디렉토리가 없으면 생성
            }

            File file = new File(profilePath + filename);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(decodedBytes);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 저장에 실패했습니다.");
        }

        return filename;
    }

    @Override
    public List<VisitedDTO> getVisitedByMemberId(String memberId) {
        return visitedStoreRepository.getVisitedByMemberId(memberId);
    }

    @Override
    @Transactional
    public void addVisited(String memberId, String storeId) {
        visitedStoreRepository.addVisited(memberId, storeId);
    }
}
