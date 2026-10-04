package com.bus.planner.dto;
import com.bus.planner.enums.BusStatus; import jakarta.validation.constraints.*; import java.time.LocalTime;
public record BusRequest(@NotBlank String busNumber,@NotBlank String busName,@NotNull Integer routeId,@NotNull @Min(1) Integer totalSeats,@NotNull @Min(0) Integer availableSeats,@NotBlank String currentLocation,@NotNull BusStatus status,@NotNull LocalTime departureTime,@NotNull LocalTime arrivalTime) {}
