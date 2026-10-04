package com.bus.planner.dto;
import jakarta.validation.constraints.*;
public record RegisterRequest(@NotBlank String name,@NotBlank @Email String email,@NotBlank @Size(min=4,max=100) String password,@Pattern(regexp="^[0-9+ -]{7,15}$",message="Phone must contain 7-15 valid characters") String phone) {}
