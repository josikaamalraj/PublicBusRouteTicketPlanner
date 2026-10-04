package com.bus.planner.service;
import com.bus.planner.enums.*; import com.bus.planner.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class AdminService {
 private final BusRepository buses; private final RouteRepository routes; private final BookingRepository bookings;
 public AdminService(BusRepository b,RouteRepository r,BookingRepository bk){buses=b;routes=r;bookings=bk;}
 public Map<String,Object> dashboard(){Map<String,Object> m=new LinkedHashMap<>();m.put("totalBuses",buses.count());m.put("availableBuses",buses.countByStatus(BusStatus.Available));m.put("runningBuses",buses.countByStatus(BusStatus.Running));m.put("delayedBuses",buses.countByStatus(BusStatus.Delayed));m.put("cancelledBuses",buses.countByStatus(BusStatus.Cancelled));m.put("totalRoutes",routes.count());m.put("totalBookings",bookings.count());m.put("confirmedBookings",bookings.countByStatus(BookingStatus.Confirmed));m.put("cancelledBookings",bookings.countByStatus(BookingStatus.Cancelled));m.put("completedBookings",bookings.countByStatus(BookingStatus.Completed));m.put("bookingsByRoute",rows(bookings.bookingsByRoute()));m.put("bookingsByBus",rows(bookings.bookingsByBus()));m.put("bookingsByStatus",rows(bookings.bookingsByStatus()));return m;}
 private List<Map<String,Object>> rows(List<Object[]> rows){List<Map<String,Object>> out=new ArrayList<>();for(Object[] r:rows){Map<String,Object> m=new LinkedHashMap<>();m.put("name",r[0].toString());m.put("total",r[1]);out.add(m);}return out;}
}
