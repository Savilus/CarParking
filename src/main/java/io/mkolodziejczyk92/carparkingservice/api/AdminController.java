package io.mkolodziejczyk92.carparkingservice.api;

import io.mkolodziejczyk92.carparkingservice.api.dto.CarParksIdDto;
import io.mkolodziejczyk92.carparkingservice.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Parking> addParking(@Valid @RequestBody CarParkingRequest request) {
        Parking parking = parkingService.createParking
                (new ParkingCoordinates(request.getParkingLatitude(), request.getParkingLongitude()),
                        new ParkingLocation(request.getParkingStreet(), request.getParkingNumber()), request.getParkingName());
        return ResponseEntity.created(URI.create("/parkings/"
                + parking.getParkingId().rawValue())).body(parking);
    }

    @PatchMapping(value = "/parkings/{parkingId}")
    public ResponseEntity<Parking> updateParkingValues(@PathVariable String parkingId,
                                                         @RequestParam String parkingName) {
        ParkingId parkingId1 = new ParkingId(parkingId);
        return ResponseEntity.ok().body(parkingService.updateParking(parkingId1, parkingName));
    }
    @DeleteMapping(value = "/parkings/{parkingId}")
    public ResponseEntity<Parking> deleteParkingValues(@PathVariable String parkingId){
        ParkingId parkingId1 = new ParkingId(parkingId);
        parkingService.deleteParking(parkingId1);
        return ResponseEntity.noContent().build();
    }


}
