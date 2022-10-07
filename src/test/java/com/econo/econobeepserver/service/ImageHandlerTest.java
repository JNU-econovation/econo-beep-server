package com.econo.econobeepserver.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

@ExtendWith(MockitoExtension.class)
class ImageHandlerTest {

    @InjectMocks
    private ImageHandler imageHandler;


    public static final String ABSOLUTE_PATH = new File("").getAbsolutePath();
    public static final String TEST_IMAGES_PATH = ABSOLUTE_PATH + "/src/test/java/com/econo/econobeepserver/images/";
    public static final String THUMBNAIL_NAME = "testThumbnail.jpg";
    public static final String THUMBNAIL_PATH = TEST_IMAGES_PATH + THUMBNAIL_NAME;
    public static final String UPDATED_THUMBNAIL_NAME = "updateTestThumbnail.jpg";
    public static final String UPDATED_THUMBNAIL_PATH = TEST_IMAGES_PATH + UPDATED_THUMBNAIL_NAME;


    @DisplayName("parseThumbnail 작동 테스트")
    @Test
    void test_parseThumbnail() {
        // given
//        MockMultipartFile mockMultipartFile = new MockMultipartFile(THUMBNAIL_NAME, THUMBNAIL_NAME, "image/jpg", new FileInputStream(THUMBNAIL_PATH);
    }
}
