package com.vrms.repo;
import com.vrms.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
 List<Vehicle> findByStatus(String status);
 @Query("select count(b) from Booking b where b.vehicle.id=:vehicleId and b.status not in ('CANCELLED','REJECTED') and b.startDate < :endDate and b.endDate > :startDate")
 long countOverlaps(@Param("vehicleId") Long vehicleId,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
}
