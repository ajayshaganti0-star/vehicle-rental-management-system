package com.vrms.service;
import com.vrms.model.*;
import com.vrms.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class SeedData implements CommandLineRunner {
 private final UserRepository users;private final VehicleRepository vehicles;private final PasswordEncoder encoder;
 public SeedData(UserRepository u,VehicleRepository v,PasswordEncoder e){users=u;vehicles=v;encoder=e;}
 public void run(String...args){
  if(users.findByEmail("admin@vrms.local").isEmpty())users.save(new User("VRMS Admin","admin@vrms.local",encoder.encode("Admin123!"),"ADMIN"));
  if(users.findByEmail("customer@vrms.local").isEmpty())users.save(new User("Demo Customer","customer@vrms.local",encoder.encode("Customer123!"),"CUSTOMER"));
  if(vehicles.count()==0){
   add("Hyundai Creta","Hyundai","SUV",2800,5,"Automatic","Petrol","https://images.unsplash.com/photo-1606152421802-db97b9c7a11b?auto=format&fit=crop&w=1000&q=80",2024);
   add("Honda City","Honda","Sedan",2200,5,"Automatic","Petrol","https://images.unsplash.com/photo-1590362891991-f776e747a588?auto=format&fit=crop&w=1000&q=80",2023);
   add("Maruti Swift","Maruti Suzuki","Hatchback",1500,5,"Manual","Petrol","https://images.unsplash.com/photo-1549317661-bd32c8ce0db2?auto=format&fit=crop&w=1000&q=80",2023);
   add("Toyota Innova Crysta","Toyota","MUV",3600,7,"Manual","Diesel","https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?auto=format&fit=crop&w=1000&q=80",2024);
   add("Kia Seltos","Kia","SUV",3000,5,"Automatic","Diesel","https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?auto=format&fit=crop&w=1000&q=80",2024);
   add("Tata Nexon EV","Tata","Electric",3200,5,"Automatic","Electric","https://images.unsplash.com/photo-1593941707882-a5bba14938c7?auto=format&fit=crop&w=1000&q=80",2025);
  }
 }
 private void add(String n,String b,String c,double p,int seats,String trans,String fuel,String img,int year){Vehicle v=new Vehicle();v.name=n;v.brand=b;v.category=c;v.pricePerDay=p;v.seats=seats;v.transmission=trans;v.fuelType=fuel;v.imageUrl=img;v.year=year;v.location="Hyderabad";v.status="AVAILABLE";vehicles.save(v);}
}
