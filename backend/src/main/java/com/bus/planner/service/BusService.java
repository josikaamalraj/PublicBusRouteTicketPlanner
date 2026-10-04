package com.bus.planner.service;
import com.bus.planner.dto.*; import com.bus.planner.entity.*; import com.bus.planner.exception.ResourceNotFoundException; import com.bus.planner.repository.*; import org.springframework.stereotype.Service; import java.time.LocalDate; import java.util.*;
@Service public class BusService {
 private final BusRepository buses; private final RouteRepository routes; private final BookingRepository bookings;
 public BusService(BusRepository b,RouteRepository r,BookingRepository bk){buses=b;routes=r;bookings=bk;}
 public List<Bus> all(){return buses.findAll();}
 public List<Bus> search(String source,String destination,LocalDate date){if(source.equalsIgnoreCase(destination)) throw new IllegalArgumentException("Source and destination cannot be the same"); return buses.search(source.trim(),destination.trim());}
 public Bus get(Integer id){return buses.findById(id).orElseThrow(()->new ResourceNotFoundException("Bus not found"));}
 public Bus create(BusRequest r){Route route=route(r.routeId());validateSeats(r.totalSeats(),r.availableSeats());Bus b=new Bus();apply(b,r,route);return buses.save(b);}
 public Bus update(Integer id,BusRequest r){Bus b=get(id);Route route=route(r.routeId());validateSeats(r.totalSeats(),r.availableSeats());apply(b,r,route);return buses.save(b);}
 public void delete(Integer id){buses.delete(get(id));}
 public Bus location(Integer id,String location){if(location==null||location.isBlank())throw new IllegalArgumentException("Location is required");Bus b=get(id);b.setCurrentLocation(location.trim());return buses.save(b);}
 public Bus status(Integer id,com.bus.planner.enums.BusStatus status){Bus b=get(id);b.setStatus(status);return buses.save(b);}
 private Route route(Integer id){return routes.findById(id).orElseThrow(()->new ResourceNotFoundException("Route not found"));}
 private void validateSeats(int total,int available){if(available>total)throw new IllegalArgumentException("Available seats cannot exceed total seats");}
 private void apply(Bus b,BusRequest r,Route route){b.setBusNumber(r.busNumber());b.setBusName(r.busName());b.setRoute(route);b.setTotalSeats(r.totalSeats());b.setAvailableSeats(r.availableSeats());b.setCurrentLocation(r.currentLocation());b.setStatus(r.status());b.setDepartureTime(r.departureTime());b.setArrivalTime(r.arrivalTime());}
 public Map<String,Object> details(Bus b){Map<String,Object> m=new LinkedHashMap<>();m.put("id",b.getId());m.put("busNumber",b.getBusNumber());m.put("busName",b.getBusName());m.put("source",b.getRoute().getSource());m.put("destination",b.getRoute().getDestination());m.put("fare",b.getRoute().getFare());m.put("availableSeats",b.getAvailableSeats());m.put("totalSeats",b.getTotalSeats());m.put("currentLocation",b.getCurrentLocation());m.put("status",b.getStatus());m.put("departureTime",b.getDepartureTime());m.put("arrivalTime",b.getArrivalTime());m.put("estimatedDuration",b.getRoute().getEstimatedDuration());return m;}
 public Set<Integer> bookedSeats(Integer busId,LocalDate date){Set<Integer> s=new HashSet<>();for(var b:bookings.findAll())if(b.getBus().getId().equals(busId)&&b.getTravelDate().equals(date)&&b.getStatus()!=com.bus.planner.enums.BookingStatus.Cancelled)s.add(b.getSeatNumber());return s;}
}
