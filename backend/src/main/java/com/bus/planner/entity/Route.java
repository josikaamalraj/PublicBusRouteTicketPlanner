package com.bus.planner.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="routes")
public class Route {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
 @Column(name="route_name",nullable=false,length=150) private String routeName;
 @Column(nullable=false,length=100) private String source;
 @Column(nullable=false,length=100) private String destination;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal fare;
 @Column(name="estimated_duration",length=50) private String estimatedDuration;
 @Column(name="created_at",insertable=false,updatable=false) private LocalDateTime createdAt;
 public Route() {}
 public Integer getId(){return id;} public void setId(Integer id){this.id=id;}
 public String getRouteName(){return routeName;} public void setRouteName(String v){routeName=v;}
 public String getSource(){return source;} public void setSource(String v){source=v;}
 public String getDestination(){return destination;} public void setDestination(String v){destination=v;}
 public BigDecimal getFare(){return fare;} public void setFare(BigDecimal v){fare=v;}
 public String getEstimatedDuration(){return estimatedDuration;} public void setEstimatedDuration(String v){estimatedDuration=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
