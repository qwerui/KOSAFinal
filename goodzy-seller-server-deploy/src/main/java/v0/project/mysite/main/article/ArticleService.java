package v0.project.mysite.main.article;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import v0.project.mysite.main.article.dto.ArticleDetailDTO;
import v0.project.mysite.main.article.dto.ArticleListDTO;
import v0.project.mysite.main.article.dto.CreateArticleReq;
import v0.project.mysite.main.category.CategoryRepository;
import v0.project.mysite.main.common.entity.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2

public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ProductRepository productRepository;
    private final ArticleImageRepository articleImageRepository;
    private final CategoryRepository categoryRepository;

    @Value("${image.article}") // 파일 저장 경로
    private String uploadDir;

    // 상점 생성
    @Transactional
    public Long createArticle(CreateArticleReq createArticleReq) {
        Article article = new Article(
                createArticleReq.title(),
                createArticleReq.startDate(),
                createArticleReq.endDate(),
                createArticleReq.shipCost(),
                createArticleReq.storeId(),
                createArticleReq.description(),
                createArticleReq.price(),
                createArticleReq.optionName1(),
                createArticleReq.optionName2(),
                createArticleReq.optionName3(),
                createArticleReq.isPublished(),
                createArticleReq.categoryId()
        );

        articleRepository.save(article);

        // 저장된 article_id 가져오기
        Long article_id = article.getArticleId();

        List<Product> products = new ArrayList<>();
        createArticleReq.products().forEach(productRecord -> {
            Product product = new Product(article_id, productRecord);
            products.add(product);
        });

        productRepository.saveAll(products);

        // 1. Multipart 파일로 받는 경우 로직
//        List<String> fileNameList = new ArrayList<>();

        // MultipartFile로 넘어온 파일 처리
//        createArticleReq.articleImages().forEach(file -> {
//            // 파일명 생성 (중복 방지)
//            String fileName = createFileName(file.getOriginalFilename());
//
//            // 로컬 파일 시스템에 저장
//            try {
//                Path directoryPath = Paths.get(uploadDir);
//                if (!Files.exists(directoryPath)) {
//                    Files.createDirectories(directoryPath); // 디렉토리가 없으면 생성
//                }
//
//                // 저장할 파일 객체 생성
//                File fileObj = new File(uploadDir + fileName);
//
//                // 파일 저장
//                file.transferTo(fileObj);
//
//                // 저장된 파일 경로를 ArticleImage에 저장
//                ArticleImage articleImage = new ArticleImage(new ArticleImageId(article_id, ), fileObj.getPath());
//
//                articleImageRepository.save(articleImage); // ArticleImage 저장
//
//                // 저장된 파일 이름 리스트에 추가
//                fileNameList.add(fileName);
//
//            } catch (IOException e) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 저장에 실패했습니다.");
//            }
//        });


        // 2. 이미지를 Base64로 받는 경우 다음 로직 사용
        List<String> fileNameList = new ArrayList<>();

        for(long i = 0; i<createArticleReq.articleImages().size(); i++) {
            // 파일 확장자 추출
            var base64Image = createArticleReq.articleImages().get((int)i);
            String fileExtension = ""; // 파일 확장자를 저장할 변수

            // Base64 문자열의 MIME 타입을 기반으로 파일 확장자를 결정
            if (base64Image.startsWith("data:image/png;")) {
                fileExtension = ".png";
            } else if (base64Image.startsWith("data:image/jpeg;")) {
                fileExtension = ".jpg";
            } else if (base64Image.startsWith("data:image/gif;")) {
                fileExtension = ".gif";
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "지원하지 않는 이미지 형식입니다.");
            }

            // 파일명 생성
            String fileName = createFileName(article_id, "image" + fileExtension); // 파일 이름 생성

            // base64 디코딩
            String base64Data = base64Image.split(",")[1]; // prefix 제거
            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

            // 로컬 파일 시스템에 저장
            try {
                Path directoryPath = Paths.get(uploadDir);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath); // 디렉토리가 없으면 생성
                }

                File file = new File(uploadDir + fileName);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(decodedBytes);
                }

                // 저장된 파일 경로 ArticleImage에 저장
                ArticleImage articleImage = new ArticleImage(new ArticleImageId(i+1, article_id), fileName);// 저장된 파일 경로 설정

                articleImageRepository.save(articleImage); // ArticleImage 저장

            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 저장에 실패했습니다.");
            }

            // 저장한 파일 이름 리스트에 추가
            fileNameList.add(fileName);
        }
        return article_id;
    }

    public List<Category> getProductCategories() {
        return categoryRepository.findAllByType("product");
    }

    @Transactional
    public void toggleArticle(Long articleId, Boolean isOpen) {
        var article = articleRepository.findFirstByArticleId(articleId);
        article.setOpened(isOpen);
    }

    public ArticleDetailDTO getArticleDetail(Long articleId) {
        ArticleDetailDTO dto = new ArticleDetailDTO();

        dto.setArticle(articleRepository.findFirstByArticleId(articleId));
        dto.setProducts(productRepository.findAllByArticleId(articleId));
        dto.setImages(articleImageRepository.findAllByArticleId(articleId));

        return dto;
    }



    // 파일 확장자 추출용
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    // 파일 이름 생성
    private String createFileName(Long articleId, String fileName) {
        // 난수화 된 파일 이름으로 통일
        return articleId+"_"+UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    public List<ArticleListDTO> getArticles(String storeId) {

        List<ArticleListDTO> dtos = new ArrayList<>();

        for(var article : articleRepository.findAllByStoreId(storeId)) {

            ArticleListDTO dto = new ArticleListDTO();

            dto.setArticle(article);

            dto.setImagePath(articleImageRepository.findFirstByArticleId(article.getArticleId()));

            dtos.add(dto);
        }

        return dtos;
    }
}
