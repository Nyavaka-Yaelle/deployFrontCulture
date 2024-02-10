package com.example.client.controller.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class FileController {

    @GetMapping("/file/content")
    public String getFileContent() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/page/editType.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}