package com.example.entity.model;

import lombok.*;
import org.springframework.core.io.Resource;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Resource airline;
    private Resource airplane;
    private Resource pilot;
    private Resource copilot;
}
