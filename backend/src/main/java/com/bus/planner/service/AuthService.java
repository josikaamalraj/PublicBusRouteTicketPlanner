package com.bus.planner.service;
import com.bus.planner.dto.*; import com.bus.planner.entity.User; import com.bus.planner.enums.UserRole; import com.bus.planner.exception.ResourceNotFoundException; import com.bus.planner.repository.UserRepository; import org.springframework.stereotype.Service;
import java.util.*;
@Service public class AuthService {
 private final UserRepository repo; public AuthService(UserRepository repo){this.repo=repo;}
 public Map<String,Object> register(RegisterRequest r){if(repo.existsByEmail(r.email())) throw new IllegalArgumentException("Email is already registered"); User u=new User();u.setName(r.name());u.setEmail(r.email());u.setPassword(r.password());u.setPhone(r.phone());u.setRole(UserRole.passenger);u=repo.save(u);return response(u,"Registration successful");}
 public Map<String,Object> login(LoginRequest r){User u=repo.findByEmail(r.email()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));if(!u.getPassword().equals(r.password())) throw new IllegalArgumentException("Invalid email or password");return response(u,"Login successful");}
 private Map<String,Object> response(User u,String message){Map<String,Object> m=new LinkedHashMap<>();m.put("message",message);m.put("user",Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"phone",u.getPhone()==null?"":u.getPhone(),"role",u.getRole().name()));return m;}
 public User getUser(Integer id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));}
}
