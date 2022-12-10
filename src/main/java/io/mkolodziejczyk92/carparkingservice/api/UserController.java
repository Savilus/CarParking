package io.mkolodziejczyk92.carparkingservice.api;

import io.mkolodziejczyk92.carparkingservice.domain.Parking;
import io.mkolodziejczyk92.carparkingservice.domain.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parkings")
@AllArgsConstructor
public class UserController {

    private final ParkingService parkingService;

    @GetMapping
    public ResponseEntity<Page<Parking>> pageResponseEntity(){
        return ResponseEntity.ok().body(parkingService.getAllParking());
    }
}
