package com.example.repository;

import com.example.entity.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Integer> {

    Optional<Pilot> findByName(String Name);

}