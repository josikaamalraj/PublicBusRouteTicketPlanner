package com.bus.planner.controller;
import com.bus.planner.dto.*; import com.bus.planner.entity.*; import com.bus.planner.enums.*; import com.bus.planner.service.*; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/admin") public class AdminController {
 private final BusService bus;private final AdminService admin;private final com.bus.planner.repository.RouteRepository routes;private final BookingService booking; private final com.bus.planner.repository.BookingRepository bookingRepository;
 public AdminController(BusService bus,AdminService admin,com.bus.planner.repository.RouteRepository routes,BookingService booking,com.bus.planner.repository.BookingRepository bookingRepository){this.bus=bus;this.admin=admin;this.routes=routes;this.booking=booking;this.bookingRepository=bookingRepository;}
 @GetMapping("/buses") List<?> buses(){return bus.all().stream().map(bus::details).toList();}
 @PostMapping("/buses") ResponseEntity<?> addBus(@Valid @RequestBody BusRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(bus.create(r));}
 @PutMapping("/buses/{id}") Bus updateBus(@PathVariable Integer id,@Valid @RequestBody BusRequest r){return bus.update(id,r);}
 @DeleteMapping("/buses/{id}") ResponseEntity<?> deleteBus(@PathVariable Integer id){bus.delete(id);return ResponseEntity.ok(Map.of("message","Bus deleted successfully"));}
 @PutMapping("/buses/{id}/location") Bus location(@PathVariable Integer id,@RequestBody Map<String,String> body){return bus.location(id,body.get("location"));}
 @PutMapping("/buses/{id}/status") Bus status(@PathVariable Integer id,@RequestBody Map<String,String> body){return bus.status(id,BusStatus.valueOf(body.get("status")));}
 @GetMapping("/routes") List<Route> routes(){return routes.findAll();}
 @PostMapping("/routes") ResponseEntity<?> addRoute(@Valid @RequestBody RouteRequest r){Route x=new Route();apply(x,r);return ResponseEntity.status(HttpStatus.CREATED).body(routes.save(x));}
 @PutMapping("/routes/{id}") Route updateRoute(@PathVariable Integer id,@Valid @RequestBody RouteRequest r){Route x=routes.findById(id).orElseThrow(()->new com.bus.planner.exception.ResourceNotFoundException("Route not found"));apply(x,r);return routes.save(x);}
 @DeleteMapping("/routes/{id}") ResponseEntity<?> deleteRoute(@PathVariable Integer id){routes.delete(routes.findById(id).orElseThrow(()->new com.bus.planner.exception.ResourceNotFoundException("Route not found")));return ResponseEntity.ok(Map.of("message","Route deleted successfully"));}
 @GetMapping("/bookings") List<Booking> bookings(){return bookingList();}
 @GetMapping("/bookings/{id}") Booking booking(@PathVariable Integer id){return booking.get(id);}
 @PutMapping("/bookings/{id}/status") Booking bookingStatus(@PathVariable Integer id,@Valid @RequestBody BookingStatusRequest r){return booking.adminStatus(id,r.status());}
 @GetMapping("/dashboard") Map<String,Object> dashboard(){return admin.dashboard();}
 private List<Booking> bookingList(){return bookingRepository.findAllDetailed();}
 private void apply(Route x,RouteRequest r){x.setRouteName(r.routeName());x.setSource(r.source());x.setDestination(r.destination());x.setFare(r.fare());x.setEstimatedDuration(r.estimatedDuration());}
}
