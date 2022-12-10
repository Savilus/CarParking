package io.mkolodziejczyk92.carparkingservice.api;

import io.mkolodziejczyk92.carparkingservice.api.dto.CarParksIdDto;
import io.mkolodziejczyk92.carparkingservice.domain.CarParkingRequest;
import io.mkolodziejczyk92.carparkingservice.domain.Parking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private CarParkingRequest carParkingRequest = CarParkingRequest.builder()
            .parkingLatitude("30")
            .parkingLongitude("50")
                .parkingNumber("17")
                .parkingStreet("Sezamkowa")
                .parkingName("CornerParking")
                .build();

    @Test
    void whenAdminAddCarParkApplicationShouldReturnCarParkId() {
        //given
        String postCarParkUrl = "/admin/parkings";

        String expectedParkingName = "CornerParking";
        String expectedStreet = "Sezamkowa";
        String expectedNumber = "17";
        String expectedLatitude = "30";
        String expectedLongitude = "50";
        HttpEntity<CarParkingRequest> carParkingRequestHttpEntity = new HttpEntity<>(carParkingRequest);

        //when
        ResponseEntity<Parking> carParksIdDtoResponseEntity =
                restTemplate.postForEntity(postCarParkUrl, carParkingRequestHttpEntity  , Parking.class);

        //then
        assertThat(carParksIdDtoResponseEntity.getStatusCode().equals(HttpStatus.CREATED)).isTrue();
        assertThat(carParksIdDtoResponseEntity.getBody()).isNotNull();
        assertThat(carParksIdDtoResponseEntity.getBody().getParkingName()).isEqualTo(expectedParkingName);
        assertThat(carParksIdDtoResponseEntity.getBody().getParkingLocation().street()).isEqualTo(expectedStreet);
        assertThat(carParksIdDtoResponseEntity.getBody().getParkingLocation().plotNumber()).isEqualTo(expectedNumber);
        assertThat(carParksIdDtoResponseEntity.getBody().getParkingCoordinates().latitude()).isEqualTo(expectedLatitude);
        assertThat(carParksIdDtoResponseEntity.getBody().getParkingCoordinates().longitude()).isEqualTo(expectedLongitude);
        assertThat(carParksIdDtoResponseEntity.getHeaders().get(HttpHeaders.LOCATION)).isNotNull();
        assertThat(carParksIdDtoResponseEntity.getHeaders().getLocation().toString().startsWith("/parkings"));

    }

    @Test
    void whenAdminAddCarParkApplicationWithEmptyName() {
        //given
        String postCarParkUrl = "/admin/parkings";

        carParkingRequest.setParkingName("");
        HttpEntity<CarParkingRequest> carParkingRequestHttpEntity = new HttpEntity<>(carParkingRequest);

        //when
        ResponseEntity<Parking> carParksIdDtoResponseEntity =
                restTemplate.postForEntity(postCarParkUrl, carParkingRequestHttpEntity  , Parking.class);

        //then
        assertThat(carParksIdDtoResponseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)).isTrue();


    }

}


