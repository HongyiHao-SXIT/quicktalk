package com.controller;

import com.common.api.R;
import com.common.exception.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@RequestMapping("file")
public class FileController {
    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = file.getInputStream();
            byte[] buffer = new byte[in.available()];
            in.read(buffer);

            String upload = System.getProperty("user.dir") + File.separator
                    + "target" + File.separator
                    + "classes" + File.separator
                    + "static" + File.separator
                    + "upload";
            upload += File.separator + file.getOriginalFilename();

            out = new FileOutputStream(upload);
            out.write(buffer);

            return R.data("/upload/" + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new ServiceException("");
    }
}

