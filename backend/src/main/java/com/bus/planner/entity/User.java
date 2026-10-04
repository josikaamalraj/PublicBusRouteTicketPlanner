package com.bus.planner.entity;

import com.bus.planner.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
 @Column(nullable=false,length=100) private String name;
 @Column(nullable=false,unique=true,length=100) private String email;
 @JsonIgnore @Column(nullable=false,length=255) private String password;
 @Column(length=15) private String phone;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private UserRole role=UserRole.passenger;
 @Column(name="created_at",insertable=false,updatable=false) private LocalDateTime createdAt;
 public User() {}
 public Integer getId(){return id;} public void setId(Integer id){this.id=id;}
 public String getName(){return name;} public void setName(String name){this.name=name;}
 public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
 public String getPassword(){return password;} public void setPassword(String password){this.password=password;}
 public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
 public UserRole getRole(){return role;} public void setRole(UserRole role){this.role=role;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
