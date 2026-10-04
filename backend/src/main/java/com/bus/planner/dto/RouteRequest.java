package com.bus.planner.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
public record RouteRequest(@NotBlank String routeName,@NotBlank String source,@NotBlank String destination,@NotNull @DecimalMin("0.0") BigDecimal fare,@NotBlank String estimatedDuration) {}
