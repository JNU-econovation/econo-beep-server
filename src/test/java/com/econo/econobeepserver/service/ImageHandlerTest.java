package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Rentee.RenteeThumbnail;
import com.econo.econobeepserver.exception.ImageIOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImageHandlerTest {

    @Spy
    private ImageHandler imageHandler;


    public static final String ABSOLUTE_PATH = new File("").getAbsolutePath() + "/";
    public static final String RENTEE_THUMBNAIL_FOLDER_PATH = ABSOLUTE_PATH + "images/rentee/thumbnail/";
    public static final String TEST_IMAGES_FOLDER_PATH = ABSOLUTE_PATH + "/src/test/java/com/econo/econobeepserver/images/";

    public static final String TEST_JPG_NAME = "test.jpg";
    public static final String TEST_JPG_PATH = TEST_IMAGES_FOLDER_PATH + TEST_JPG_NAME;
    public static final String TEST_MP4_NAME = "test.mp4";
    public static final String TEST_MP4_PATH = TEST_IMAGES_FOLDER_PATH + TEST_MP4_NAME;
    public static final String UPDATE_TEST_JPG_NAME = "updateTest.jpg";
    public static final String UPDATE_TEST_JPG_PATH = TEST_IMAGES_FOLDER_PATH + UPDATE_TEST_JPG_NAME;

    @Nested
    @DisplayName("parseRenteeThumbnail 테스트")
    class ParseThumbnailTest {

        @DisplayName("정상동작 하는지")
        @Test
        void test() throws IOException {
            // given
            MockMultipartFile mockMultipartFile = new MockMultipartFile(TEST_JPG_NAME, TEST_JPG_NAME, "image/jpg", new FileInputStream(TEST_JPG_PATH));

            // when & then
            assertDoesNotThrow(() -> {
                RenteeThumbnail renteeThumbnail = imageHandler.parseRenteeThumbnail(mockMultipartFile);

                String[] folderPaths = renteeThumbnail.getFilePath().split("/");
                folderPaths = Arrays.copyOf(folderPaths, folderPaths.length - 1);
                String folderPath = String.join("/", folderPaths) + "/";
                assertEquals(RENTEE_THUMBNAIL_FOLDER_PATH, folderPath);
            });
        }

        @DisplayName("지원하지않는 이미지 확장자를 캐치하는지")
        @Test
        void unsupportedFileExtensionTest() throws IOException {
            // given
            MockMultipartFile mockMultipartFile = new MockMultipartFile(TEST_MP4_NAME, new FileInputStream(TEST_MP4_PATH));

            // when
            assertThrows(ImageIOException.class, () -> imageHandler.parseRenteeThumbnail(mockMultipartFile));
        }
    }
}
