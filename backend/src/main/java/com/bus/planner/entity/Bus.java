package com.bus.planner.entity;

import com.bus.planner.enums.BusStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="buses")
public class Bus {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
 @Column(name="bus_number",nullable=false,unique=true,length=50) private String busNumber;
 @Column(name="bus_name",length=100) private String busName;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="route_id",nullable=false) @JsonIgnoreProperties({"createdAt"}) private Route route;
 @Column(name="total_seats",nullable=false) private Integer totalSeats;
 @Column(name="available_seats",nullable=false) private Integer availableSeats;
 @Column(name="current_location",length=150) private String currentLocation;
 @Enumerated(EnumType.STRING) @Column(length=20) private BusStatus status=BusStatus.Available;
 @Column(name="departure_time") private LocalTime departureTime;
 @Column(name="arrival_time") private LocalTime arrivalTime;
 @Column(name="created_at",insertable=false,updatable=false) private LocalDateTime createdAt;
 public Bus() {}
 public Integer getId(){return id;} public void setId(Integer id){this.id=id;}
 public String getBusNumber(){return busNumber;} public void setBusNumber(String v){busNumber=v;}
 public String getBusName(){return busName;} public void setBusName(String v){busName=v;}
 public Route getRoute(){return route;} public void setRoute(Route v){route=v;}
 public Integer getTotalSeats(){return totalSeats;} public void setTotalSeats(Integer v){totalSeats=v;}
 public Integer getAvailableSeats(){return availableSeats;} public void setAvailableSeats(Integer v){availableSeats=v;}
 public String getCurrentLocation(){return currentLocation;} public void setCurrentLocation(String v){currentLocation=v;}
 public BusStatus getStatus(){return status;} public void setStatus(BusStatus v){status=v;}
 public LocalTime getDepartureTime(){return departureTime;} public void setDepartureTime(LocalTime v){departureTime=v;}
 public LocalTime getArrivalTime(){return arrivalTime;} public void setArrivalTime(LocalTime v){arrivalTime=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
