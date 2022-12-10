package io.mkolodziejczyk92.carparkingservice.domain;

import lombok.Builder;

@Builder
public record ParkingCoordinates(String latitude, String longitude) {

}
