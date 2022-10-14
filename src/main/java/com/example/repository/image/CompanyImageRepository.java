package com.example.repository.image;

import com.example.entity.model.image.CompanyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyImageRepository extends JpaRepository<CompanyImage, Integer> {

    Optional<CompanyImage> findByName(String name);

}