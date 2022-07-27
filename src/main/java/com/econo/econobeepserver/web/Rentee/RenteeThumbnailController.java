package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.exception.ImageIOException;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@RestController
@RequiredArgsConstructor
public class RenteeThumbnailController {

    private final RenteeService renteeService;

    @GetMapping("/rentee/{id}/thumbnail")
    public void getRenteeCoverImageByRenteeId(HttpServletResponse response, @PathVariable(value = "id") Long id) {
        String imageFilePath = renteeService.getThumbnailFilePathByRenteeId(id);

        outputImage(response, imageFilePath);
    }

    private void outputImage(HttpServletResponse response, String imagePath) {
        File file = new File(imagePath);
        if (!file.isFile()) {
            throw new ImageIOException("이미지를 불러오는 중에 문제가 생겼습니다.");
        }

        FileInputStream fis = null;
        BufferedInputStream in = null;
        ByteArrayOutputStream bStream = null;
        try {
            fis = new FileInputStream(file);
            in = new BufferedInputStream(fis);
            bStream = new ByteArrayOutputStream();
            int imgByte;
            while ((imgByte = in.read()) != -1) {
                bStream.write(imgByte);
            }

            String type = "";
            String ext = FilenameUtils.getExtension(file.getName());
            if (!ext.isEmpty()) {
                if (ext.equalsIgnoreCase("jpg")) {
                    type = "image/jpeg";
                } else {
                    type = "image/" + ext.toLowerCase();
                }
            }

            response.setHeader("Content-Type", type);
            response.setContentLength(bStream.size());
            bStream.writeTo(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            throw new ImageIOException(e.getMessage());
        } finally {
            try {
                if (bStream != null) {
                    bStream.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                throw new ImageIOException(e.getMessage());
            }
        }
    }
}
