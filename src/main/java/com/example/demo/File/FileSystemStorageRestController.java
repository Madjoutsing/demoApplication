package com.example.demo.File;

import com.google.common.io.Files;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileSystemStorageRestController {
    private final FileSystemStorageService fileSystemStorageService;

    public FileSystemStorageRestController(FileSystemStorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
    }


    @PostMapping("/file")
    public ResponseEntity<?> test1(
            MultipartFile file) throws IOException {
        return fileSystemStorageService.store(file);
    }

    @GetMapping("/files")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@RequestParam String filename) throws Exception {

        Resource file = fileSystemStorageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/fileCatalogue")
    @ResponseBody
    public ResponseEntity<Resource> serveFileCatalogue(@RequestParam String filename) throws Exception {

        Resource file = fileSystemStorageService.loadAsResource(filename);
        String ext = Files.getFileExtension(filename);
        if(ext.equals("jpg")) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg").body(file);
        }

        if(ext.equals("jpeg")) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
        }
        if(ext.equals("png")){
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/png").body(file);

        }

        if(ext.equals("pdf")){
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/pdf").body(file);

        }

        if(ext.equals("docx")){
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document").body(file);

        }

        return null;

    }


}
