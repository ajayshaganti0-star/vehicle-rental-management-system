package com.vrms.controller;
import com.vrms.model.*;
import com.vrms.repo.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/admin") @PreAuthorize("hasRole('ADMIN')")
public class AdminController {
 private final VehicleRepository vehicles;private final BookingRepository bookings;
 public AdminController(VehicleRepository v,BookingRepository b){vehicles=v;bookings=b;}
 @GetMapping("/dashboard") public Map<String,Object> dashboard(){return Map.of("totalVehicles",vehicles.count(),"availableVehicles",vehicles.findByStatus("AVAILABLE").size(),"totalBookings",bookings.count(),"totalRevenue",bookings.findAll().stream().filter(b->"CONFIRMED".equals(b.status)).mapToDouble(b->b.totalPrice).sum());}
 @PostMapping("/vehicles") public Vehicle add(@RequestBody Vehicle v){v.id=null;if(v.status==null)v.status="AVAILABLE";return vehicles.save(v);}
 @PutMapping("/vehicles/{id}") public ResponseEntity<Vehicle> update(@PathVariable Long id,@RequestBody Vehicle body){return vehicles.findById(id).map(v->{v.name=body.name;v.brand=body.brand;v.category=body.category;v.location=body.location;v.pricePerDay=body.pricePerDay;v.seats=body.seats;v.transmission=body.transmission;v.fuelType=body.fuelType;v.imageUrl=body.imageUrl;v.status=body.status;v.year=body.year;v.description=body.description;return ResponseEntity.ok(vehicles.save(v));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/vehicles/{id}") public ResponseEntity<?> delete(@PathVariable Long id){if(!vehicles.existsById(id))return ResponseEntity.notFound().build();vehicles.deleteById(id);return ResponseEntity.noContent().build();}
 @GetMapping("/bookings") public List<Map<String,Object>> allBookings(){return bookings.findAllByOrderByCreatedAtDesc().stream().map(b->Map.<String,Object>of("id",b.id,"vehicleName",b.vehicle.name,"customerEmail",b.customer.email,"startDate",b.startDate,"endDate",b.endDate,"totalPrice",b.totalPrice,"status",b.status)).toList();}
 @PatchMapping("/bookings/{id}/status") public ResponseEntity<?> status(@PathVariable Long id,@RequestParam String status){if(!Set.of("CONFIRMED","CANCELLED","REJECTED","PENDING").contains(status))return ResponseEntity.badRequest().body(Map.of("message","Invalid status."));return bookings.findById(id).map(b->{b.status=status;bookings.save(b);return ResponseEntity.ok(Map.of("id",b.id,"status",b.status));}).orElse(ResponseEntity.notFound().build());}
}
