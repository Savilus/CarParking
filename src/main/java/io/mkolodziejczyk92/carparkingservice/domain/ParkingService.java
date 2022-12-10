package io.mkolodziejczyk92.carparkingservice.domain;

import io.mkolodziejczyk92.carparkingservice.api.dto.LocationDto;
import io.mkolodziejczyk92.carparkingservice.domain.exceptions.ParkingNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Parking updateParking(ParkingId parkingId, String parkingName){
        Optional<Parking> parking = parkingRepository.findFirstByParkingIdEquals(parkingId);
        if(parking.isPresent()){
            Parking unpackedParking = parking.get();
            unpackedParking.setParkingName(parkingName);
            return parkingRepository.insert(unpackedParking);
        }else {
            throw new ParkingNotFoundException();
        }
    }

    public void deleteParking(ParkingId parkingId){
        parkingRepository.deleteByParkingIdEquals(parkingId);
    }

    public Parking getLocation(ParkingId parkingId){
        return parkingRepository.findFirstByParkingIdEquals(parkingId).orElseThrow(ParkingNotFoundException::new);
    }
}
