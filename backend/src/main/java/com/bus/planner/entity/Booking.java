package com.bus.planner.entity;

import com.bus.planner.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="bookings",uniqueConstraints=@UniqueConstraint(name="uk_booking_seat_date",columnNames={"bus_id","seat_number","travel_date"}))
public class Booking {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="user_id",nullable=false) @JsonIgnoreProperties({"password","createdAt"}) private User user;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="bus_id",nullable=false) @JsonIgnoreProperties({"createdAt"}) private Bus bus;
 @Column(name="seat_number",nullable=false) private Integer seatNumber;
 @Column(name="booking_date",insertable=false,updatable=false) private LocalDateTime bookingDate;
 @Column(name="travel_date",nullable=false) private LocalDate travelDate;
 @Column(nullable=false,length=100) private String source;
 @Column(nullable=false,length=100) private String destination;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal fare;
 @Enumerated(EnumType.STRING) @Column(length=20) private BookingStatus status=BookingStatus.Confirmed;
 public Booking() {}
 public Integer getId(){return id;} public void setId(Integer v){id=v;}
 public User getUser(){return user;} public void setUser(User v){user=v;}
 public Bus getBus(){return bus;} public void setBus(Bus v){bus=v;}
 public Integer getSeatNumber(){return seatNumber;} public void setSeatNumber(Integer v){seatNumber=v;}
 public LocalDateTime getBookingDate(){return bookingDate;}
 public LocalDate getTravelDate(){return travelDate;} public void setTravelDate(LocalDate v){travelDate=v;}
 public String getSource(){return source;} public void setSource(String v){source=v;}
 public String getDestination(){return destination;} public void setDestination(String v){destination=v;}
 public BigDecimal getFare(){return fare;} public void setFare(BigDecimal v){fare=v;}
 public BookingStatus getStatus(){return status;} public void setStatus(BookingStatus v){status=v;}
}
