package io.mkolodziejczyk92.carparkingservice.domain;

import lombok.Builder;

@Builder
public record ParkingLocation(String street, String plotNumber) {

}
