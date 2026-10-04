package com.bus.planner.repository;
import com.bus.planner.entity.Booking; import com.bus.planner.enums.BookingStatus; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param;
import java.time.LocalDate; import java.util.*;
public interface BookingRepository extends JpaRepository<Booking,Integer> {
 List<Booking> findByUserIdOrderByTravelDateDesc(Integer userId);
 boolean existsByBusIdAndSeatNumberAndTravelDateAndStatusNot(Integer busId,Integer seatNumber,LocalDate date,BookingStatus status);
 long countByStatus(BookingStatus status);
 @Query("select b from Booking b join fetch b.user u join fetch b.bus bs join fetch bs.route r order by b.travelDate desc") List<Booking> findAllDetailed();
 @Query("select r.routeName, count(b.id) from Booking b join b.bus bs join bs.route r group by r.routeName") List<Object[]> bookingsByRoute();
 @Query("select bs.busNumber, count(b.id) from Booking b join b.bus bs group by bs.busNumber") List<Object[]> bookingsByBus();
 @Query("select b.status, count(b.id) from Booking b group by b.status") List<Object[]> bookingsByStatus();
}
