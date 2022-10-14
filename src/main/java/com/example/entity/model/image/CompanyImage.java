package com.example.entity.model.image;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company_image")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Lob
    private byte[] image;
}
