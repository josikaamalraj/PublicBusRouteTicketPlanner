package com.bus.planner.exception;
import org.springframework.dao.DataIntegrityViolationException; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.util.*;
@RestControllerAdvice public class GlobalExceptionHandler {
 private ResponseEntity<Map<String,Object>> body(HttpStatus s,String msg){Map<String,Object> m=new LinkedHashMap<>();m.put("timestamp",LocalDateTime.now());m.put("status",s.value());m.put("message",msg);return ResponseEntity.status(s).body(m);}
 @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<?> notFound(ResourceNotFoundException e){return body(HttpStatus.NOT_FOUND,e.getMessage());}
 @ExceptionHandler(SeatNotAvailableException.class) ResponseEntity<?> conflict(SeatNotAvailableException e){return body(HttpStatus.CONFLICT,e.getMessage());}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> bad(IllegalArgumentException e){return body(HttpStatus.BAD_REQUEST,e.getMessage());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> validation(MethodArgumentNotValidException e){String msg=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).findFirst().orElse("Invalid request");return body(HttpStatus.BAD_REQUEST,msg);}
 @ExceptionHandler(DataIntegrityViolationException.class) ResponseEntity<?> integrity(DataIntegrityViolationException e){return body(HttpStatus.CONFLICT,"Database constraint violation. Check duplicate data or referenced records.");}
 @ExceptionHandler(Exception.class) ResponseEntity<?> generic(Exception e){return body(HttpStatus.INTERNAL_SERVER_ERROR,"Internal server error: "+e.getMessage());}
}
