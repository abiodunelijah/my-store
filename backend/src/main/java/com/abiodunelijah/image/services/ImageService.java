package com.abiodunelijah.image.services;

import com.abiodunelijah.image.dtos.ImageDto;
import com.abiodunelijah.image.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long imageId);

    void deleteImageById(Long imageId);

    void updateImage(MultipartFile file, Long imageId);

    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
}
