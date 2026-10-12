package com.vrms.model;
import jakarta.persistence.*;
@Entity @Table(name="vehicles")
public class Vehicle {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String name,brand,category,location;
 public double pricePerDay;
 public int seats=5;
 public String transmission="Manual",fuelType="Petrol";
 @Column(length=1000) public String imageUrl;
 public String status="AVAILABLE";
@Column(name = "vehicle_year")
public int year = 2025;
 @Column(length=2000) public String description;
 public Vehicle(){}
}
