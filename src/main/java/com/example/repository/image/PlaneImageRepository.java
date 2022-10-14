package com.example.repository.image;

import com.example.entity.model.image.PlaneImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneImageRepository extends JpaRepository<PlaneImage, Integer> {

    Optional<PlaneImage> findByModel(String model);

}
