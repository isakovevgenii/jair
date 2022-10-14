package com.example.repository;

import com.example.entity.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {

    List<Plane> findAllByCompany(String company);

}
