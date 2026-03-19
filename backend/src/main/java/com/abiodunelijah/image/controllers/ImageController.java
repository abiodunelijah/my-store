package com.abiodunelijah.image.controllers;

import com.abiodunelijah.image.dtos.ImageDto;
import com.abiodunelijah.image.entities.Image;
import com.abiodunelijah.image.services.ImageService;
import com.abiodunelijah.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
public class ImageController {


    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadImages(@RequestParam("files") List<MultipartFile> files,
                                                    @RequestParam("productId") Long productId) {
        List<ImageDto> imageDto = imageService.saveImages(productId, files);
        return ResponseEntity.ok(new ApiResponse("Images uploaded successfully!", imageDto));
    }

    @GetMapping("/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {

        Image image = imageService.getImageById(imageId);

        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + image.getFileName() + "\"")
                .body(resource);

    }

    @PutMapping("/update/{imageId}")
    public ResponseEntity<ApiResponse> updateImage(@RequestBody MultipartFile file, @PathVariable Long imageId) {

        imageService.updateImage(file, imageId);
        return ResponseEntity.ok(new ApiResponse("image updated successfully", null));
    }

    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImageById(imageId);
        return ResponseEntity.ok(new ApiResponse("image deleted successfully", null));
    }
}
