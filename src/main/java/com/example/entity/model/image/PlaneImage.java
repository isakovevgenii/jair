package com.example.entity.model.image;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "plane_image")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaneImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String model;
    @Lob
    private byte[] image;
}
