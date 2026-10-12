package com.vrms.repo;
import com.vrms.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BookingRepository extends JpaRepository<Booking,Long>{
 List<Booking> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
 List<Booking> findAllByOrderByCreatedAtDesc();
}
