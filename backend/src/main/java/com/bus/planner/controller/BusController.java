package com.bus.planner.controller;
import com.bus.planner.dto.*; import com.bus.planner.service.BusService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.LocalDate; import java.util.*;
@RestController @RequestMapping("/api/buses") public class BusController {private final BusService s;public BusController(BusService s){this.s=s;}
 @GetMapping List<?> all(){return s.all().stream().map(s::details).toList();}
 @GetMapping("/search") List<?> search(@RequestParam String source,@RequestParam String destination,@RequestParam(required=false) LocalDate travelDate){return s.search(source,destination,travelDate).stream().map(s::details).toList();}
 @GetMapping("/{id}") Map<String,Object> get(@PathVariable Integer id){return s.details(s.get(id));}
 @GetMapping("/{id}/booked-seats") Set<Integer> seats(@PathVariable Integer id,@RequestParam LocalDate travelDate){return s.bookedSeats(id,travelDate);}
}
