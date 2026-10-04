package com.bus.planner.dto;
import jakarta.validation.constraints.*; import java.time.LocalDate;
public record BookingRequest(@NotNull Integer userId,@NotNull Integer busId,@NotNull @Min(1) Integer seatNumber,@NotNull LocalDate travelDate,@NotBlank String source,@NotBlank String destination) {}
