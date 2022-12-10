package io.mkolodziejczyk92.carparkingservice.api;

import io.mkolodziejczyk92.carparkingservice.api.dto.LocationDto;
import io.mkolodziejczyk92.carparkingservice.domain.Parking;
import io.mkolodziejczyk92.carparkingservice.domain.ParkingId;
import io.mkolodziejczyk92.carparkingservice.domain.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/{parkingId}/location")
    public ResponseEntity<LocationDto> getLocation(@PathVariable String parkingId){
        Parking location = parkingService.getLocation(new ParkingId(parkingId));
        LocationDto locationDto = LocationDto.builder()
                .street(location.getParkingLocation().street())
                .longitude(location.getParkingCoordinates().longitude())
                .latitude(location.getParkingCoordinates().latitude())
                .plotNumber(location.getParkingLocation().plotNumber())
                .build();
        return ResponseEntity.ok().body(locationDto);
    }
}
