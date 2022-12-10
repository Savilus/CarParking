package io.mkolodziejczyk92.carparkingservice.domain;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParkingService {

    public Parking createParking(ParkingCoordinates parkingCoordinates, ParkingLocation parkingLocation, String parkingName){
       return Parking.builder()
                .parkingCoordinates(parkingCoordinates)
                .parkingLocation(parkingLocation)
                .parkingName(parkingName)
                .parkingId(ParkingId.random())
                .build();
    }
}
