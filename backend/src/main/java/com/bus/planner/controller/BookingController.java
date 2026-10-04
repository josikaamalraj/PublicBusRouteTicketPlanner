package com.bus.planner.controller;
import com.bus.planner.dto.*; import com.bus.planner.entity.Booking; import com.bus.planner.service.BookingService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/bookings") public class BookingController {private final BookingService s;public BookingController(BookingService s){this.s=s;}
 @PostMapping ResponseEntity<?> create(@Valid @RequestBody BookingRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(s.create(r));}
 @GetMapping("/user/{userId}") List<Booking> user(@PathVariable Integer userId){return s.userBookings(userId);}
 @GetMapping("/{id}") Booking get(@PathVariable Integer id){return s.get(id);}
 @PutMapping("/{id}/cancel") Booking cancel(@PathVariable Integer id){return s.cancel(id);}
}
