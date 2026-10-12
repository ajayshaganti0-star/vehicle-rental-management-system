package com.vrms.controller;
import com.vrms.model.Vehicle;
import com.vrms.repo.VehicleRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController @RequestMapping("/api/vehicles")
public class VehicleController {
 private final VehicleRepository vehicles;
 public VehicleController(VehicleRepository v){vehicles=v;}
 @GetMapping public List<Vehicle> all(@RequestParam(required=false) String search,@RequestParam(required=false) String category){
  return vehicles.findAll().stream().filter(v->v.status==null||!v.status.equals("RETIRED"))
   .filter(v->search==null||search.isBlank()||(String.valueOf(v.name)+" "+v.brand+" "+v.location).toLowerCase().contains(search.toLowerCase()))
   .filter(v->category==null||category.isBlank()||category.equalsIgnoreCase("All vehicles")||category.equalsIgnoreCase(v.category)).toList();
 }
 @GetMapping("/{id}") public Vehicle one(@PathVariable Long id){return vehicles.findById(id).orElseThrow(()->new IllegalArgumentException("Vehicle not found"));}
 @GetMapping("/{id}/availability") public boolean available(@PathVariable Long id,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
  if(!endDate.isAfter(startDate))return false;return vehicles.findById(id).filter(v->"AVAILABLE".equals(v.status)).isPresent()&&vehicles.countOverlaps(id,startDate,endDate)==0;
 }
}
