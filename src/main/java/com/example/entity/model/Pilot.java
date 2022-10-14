package com.example.entity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pilot")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String medal;
    private Integer counter;
}
