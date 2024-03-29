package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Rentee.RenteeThumbnail;
import com.econo.econobeepserver.exception.ImageIOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ImageHandler {

    private static final String ABSOLUTE_PATH = new File("").getAbsolutePath() + "/";
    public static final String RENTEE_THUMBNAIL_FOLDER_PATH = ABSOLUTE_PATH + "images/rentee/thumbnail/";


    private void validateSavingPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private boolean isSupportImageExtension(String contentType) {
        if (
                contentType.contains("image/jpg") ||
                        contentType.contains("image/jpeg") ||
                        contentType.contains("image/gif") ||
                        contentType.contains("image/png")
        ) {
            return true;
        }

        return false;
    }

    private void validateImageExtension(String contentType) {
        if (contentType == null || !isSupportImageExtension(contentType)) {
            throw new ImageIOException("업로드한 이미지의 파일이 올바르지 않습니다 : " + contentType);
        }
    }

    private String getFileExtension(String fileName) {
        String[] splitFileName = fileName.split("\\.");
        if (splitFileName.length <= 1) {
            return "";
        }

        return splitFileName[splitFileName.length - 1];
    }

    public RenteeThumbnail parseRenteeThumbnail(MultipartFile multipartFile) {
        validateSavingPath(RENTEE_THUMBNAIL_FOLDER_PATH);
        validateImageExtension(multipartFile.getContentType());

        String fileName = System.nanoTime() + "." + getFileExtension(multipartFile.getOriginalFilename());
        String filePath = RENTEE_THUMBNAIL_FOLDER_PATH + fileName;
        return RenteeThumbnail.builder()
                .filePath(filePath)
                .build();
    }

    public void downloadImage(MultipartFile multipartFile, String filePath) {
        try {
            File file = new File(filePath);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new ImageIOException("이미지 다운로드에 실패했습니다");
        }
    }

    public void deleteImage(String filePath) throws ImageIOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ImageIOException("존재하지 않는 이미지 파일입니다.");
        }

        if (!file.delete()) {
            throw new ImageIOException("이미지 삭제에 실패했습니다.");
        }
    }
}
