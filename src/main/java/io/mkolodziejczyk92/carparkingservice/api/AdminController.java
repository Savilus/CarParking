package io.mkolodziejczyk92.carparkingservice.api;

import io.mkolodziejczyk92.carparkingservice.api.dto.CarParksIdDto;
import io.mkolodziejczyk92.carparkingservice.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final ParkingService parkingService;

    public AdminController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping(value = "/parkings")
    public ResponseEntity<Parking> addParking(){
        Parking parking = parkingService.createParking(new ParkingCoordinates("50","30"),
                new ParkingLocation("Sezamkowa", "33"), "CornerParking");
        return ResponseEntity.created(URI.create("/parkings/"
                + parking.getParkingId().rawValue())).body(parking);
    }


}
