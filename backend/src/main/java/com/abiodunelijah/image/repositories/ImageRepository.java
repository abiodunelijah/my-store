package com.abiodunelijah.image.repositories;

import com.abiodunelijah.image.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
