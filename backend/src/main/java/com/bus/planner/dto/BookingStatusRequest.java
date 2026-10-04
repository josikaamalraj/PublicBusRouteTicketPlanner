package com.bus.planner.dto;
import com.bus.planner.enums.BookingStatus; import jakarta.validation.constraints.NotNull;
public record BookingStatusRequest(@NotNull BookingStatus status) {}
