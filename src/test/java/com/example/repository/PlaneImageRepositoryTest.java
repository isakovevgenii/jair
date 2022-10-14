package com.example.repository;

import com.example.entity.model.image.PlaneImage;
import com.example.repository.image.PlaneImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlaneImageRepositoryTest {

    @Autowired
    PlaneImageRepository planeImageRepository;

    @Test
    public void findAllPlaneImageTest() {
        Iterable planeImages = planeImageRepository.findAll();
        assertThat(planeImages).hasSize(2);
    }

    @Test
    public void findPlaneImageTest() {
        PlaneImage planeImage = planeImageRepository.findByModel("airbusa350").get();
        assertThat(planeImage.getModel()).isEqualTo("airbusa350");
    }
}
