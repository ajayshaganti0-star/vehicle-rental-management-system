package com.vrms.controller;
import com.vrms.model.*;
import com.vrms.repo.*;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
@RestController @RequestMapping("/api/bookings")
public class BookingController {
 private final BookingRepository bookings;private final VehicleRepository vehicles;private final UserRepository users;
 public BookingController(BookingRepository b,VehicleRepository v,UserRepository u){bookings=b;vehicles=v;users=u;}
 @PostMapping @Transactional public ResponseEntity<?> create(@RequestBody CreateBooking req,Authentication auth){
  if(req.startDate()==null||req.endDate()==null||!req.endDate().isAfter(req.startDate()))return ResponseEntity.badRequest().body(Map.of("message","Return date must be after pickup date."));
  Vehicle v=vehicles.findById(req.vehicleId()).orElse(null);if(v==null)return ResponseEntity.badRequest().body(Map.of("message","Vehicle not found."));
  if(!"AVAILABLE".equals(v.status)||vehicles.countOverlaps(v.id,req.startDate(),req.endDate())>0)return ResponseEntity.status(409).body(Map.of("message","Vehicle is not available for those dates."));
  User u=users.findByEmail(auth.getName()).orElseThrow();Booking b=new Booking();b.customer=u;b.vehicle=v;b.startDate=req.startDate();b.endDate=req.endDate();b.totalPrice=v.pricePerDay*ChronoUnit.DAYS.between(req.startDate(),req.endDate());b.status="PENDING";
  Booking saved=bookings.saveAndFlush(b);return ResponseEntity.ok(view(saved));
 }
 @GetMapping("/my") public List<Map<String,Object>> mine(Authentication auth){User u=users.findByEmail(auth.getName()).orElseThrow();return bookings.findByCustomerIdOrderByCreatedAtDesc(u.id).stream().map(this::view).toList();}
 private Map<String,Object> view(Booking b){return Map.of("id",b.id,"vehicleId",b.vehicle.id,"vehicleName",b.vehicle.name,"customerEmail",b.customer.email,"startDate",b.startDate,"endDate",b.endDate,"totalPrice",b.totalPrice,"status",b.status);}
 public record CreateBooking(Long vehicleId,LocalDate startDate,LocalDate endDate){}
}
