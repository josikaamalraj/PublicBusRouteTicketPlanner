package com.bus.planner.dto;
import jakarta.validation.constraints.*; import java.time.LocalDate;
public record BusSearchRequest(@NotBlank String source,@NotBlank String destination,@NotNull LocalDate travelDate) {}
