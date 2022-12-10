package io.mkolodziejczyk92.carparkingservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parking {

    ParkingCoordinates parkingCoordinates;
    ParkingLocation parkingLocation;
    ParkingId parkingId;
    String parkingName;

}
