package com.example.entity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "flight")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String airline;
    private String airplane;
    private String pilot;
    private String copilot;
}
