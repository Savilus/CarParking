package io.mkolodziejczyk92.carparkingservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ParkingService {

    ParkingRepository parkingRepository;


    public Parking createParking(ParkingCoordinates parkingCoordinates, ParkingLocation parkingLocation, String parkingName){
        Parking parking = Parking.builder()
                .parkingCoordinates(parkingCoordinates)
                .parkingLocation(parkingLocation)
                .parkingName(parkingName)
                .parkingId(ParkingId.random())
                .build();
        return parkingRepository.save(parking);
    }

    public Page<Parking> getAllParking(){
        return parkingRepository.findAll(PageRequest.of(0,100));
    }
}
