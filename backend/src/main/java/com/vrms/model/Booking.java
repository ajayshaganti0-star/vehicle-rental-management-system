package com.vrms.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity @Table(name="bookings", indexes=@Index(name="idx_booking_dates",columnList="vehicle_id,startDate,endDate"))
public class Booking {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @ManyToOne(optional=false) public User customer;
 @ManyToOne(optional=false) public Vehicle vehicle;
 public LocalDate startDate,endDate;
 public double totalPrice;
 public String status="PENDING";
 public LocalDateTime createdAt=LocalDateTime.now();
 public Booking(){}
}
