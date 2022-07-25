package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Equipment.EquipmentImage;
import com.econo.econobeepserver.exception.ImageIOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ImageHandler {

    private static final String ABSOLUTE_PATH = new File("").getAbsolutePath() + "/";
    private static final String BOOK_IMAGES_PATH = ABSOLUTE_PATH + "images/book/";
    private static final String EQUIPMENT_IMAGES_PATH = ABSOLUTE_PATH + "images/equipment/";


    private void validateSavingPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private boolean isSupportPictureContentType(String contentType) {
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

    private void validatePictureContentType(String contentType) {
        if (contentType == null || !isSupportPictureContentType(contentType)) {
            throw new ImageIOException("업로드한 이미지의 파일이 올바르지 않습니다 : " + contentType);
        }
    }

    private String getFileExtension(String contentType) {
        String[] splitedFileName = contentType.split(".");

        if (splitedFileName.length == 1) {
            return "";
        } else {
            return splitedFileName[splitedFileName.length - 1];
        }
    }

    public BookCoverImage parseBookCoverImage(MultipartFile multipartFile) {
        validateSavingPath(BOOK_IMAGES_PATH);
        validatePictureContentType(multipartFile.getContentType());

        String fileName = System.nanoTime() + multipartFile.getOriginalFilename();
        String filePath = BOOK_IMAGES_PATH + fileName;
        return BookCoverImage.builder()
                .filePath(filePath)
                .build();
    }

    public EquipmentImage parseEquipmentImage(MultipartFile multipartFile) {
        validateSavingPath(EQUIPMENT_IMAGES_PATH);
        validatePictureContentType(multipartFile.getContentType());

        String fileName = System.nanoTime() + multipartFile.getOriginalFilename();
        String filePath = EQUIPMENT_IMAGES_PATH + fileName;
        return EquipmentImage.builder()
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

    public void deleteImage(String filePath) {
        File file = new File(filePath);
        try {
            if (file.exists()) {
                if (!file.delete()) {
                    throw new RuntimeException();
                }
            }

        } catch (Exception e) {
            throw new ImageIOException("이미지 삭제에 실패했습니다.");
        }
    }
}
