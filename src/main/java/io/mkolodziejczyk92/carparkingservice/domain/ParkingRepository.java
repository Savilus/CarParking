package io.mkolodziejczyk92.carparkingservice.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends MongoRepository<Parking, ParkingId> {
}
