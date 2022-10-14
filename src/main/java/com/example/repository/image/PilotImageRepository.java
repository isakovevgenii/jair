package com.example.repository.image;

import com.example.entity.model.image.PilotImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PilotImageRepository extends JpaRepository<PilotImage, Integer> {

    Optional<PilotImage> findByName(String name);

}