package com.bus.planner.controller;
import com.bus.planner.entity.Route; import com.bus.planner.exception.ResourceNotFoundException; import com.bus.planner.repository.RouteRepository; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/routes") public class RouteController { private final RouteRepository repo; public RouteController(RouteRepository repo){this.repo=repo;} @GetMapping("/{id}") public Route get(@PathVariable Integer id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Route not found"));} }
