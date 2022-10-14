package com.example.entity.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    private Integer id;
    private String name;
    private String password;
}
