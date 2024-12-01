package com.crm.practice.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("api/files")
public class FileController {

    private final String uploadDir = "uploads/";



    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            Path path = Paths.get(uploadDir+file.getOriginalFilename());
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("File Uploaded Successfully: "+ file.getOriginalFilename());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Upload Failed");
        }
    }
}
