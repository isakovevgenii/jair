package com.example.entity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "plane")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String model;
    private String company;
    private Integer number;
}
