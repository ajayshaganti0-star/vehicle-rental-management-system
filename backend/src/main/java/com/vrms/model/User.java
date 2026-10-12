package com.vrms.model;
import jakarta.persistence.*;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String fullName;
 @Column(nullable=false,unique=true) public String email;
 @Column(nullable=false) public String password;
 @Column(nullable=false) public String role="CUSTOMER";
 public User(){}
 public User(String n,String e,String p,String r){fullName=n;email=e;password=p;role=r;}
}
