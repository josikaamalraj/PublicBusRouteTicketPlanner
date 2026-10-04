package com.bus.planner.repository;
import com.bus.planner.entity.Bus; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param;
import java.util.*;
public interface BusRepository extends JpaRepository<Bus,Integer> {
 Optional<Bus> findByBusNumber(String busNumber);
 @Query("select b from Bus b join fetch b.route r where lower(r.source)=lower(:source) and lower(r.destination)=lower(:destination)") List<Bus> search(@Param("source") String source,@Param("destination") String destination);
 long countByStatus(com.bus.planner.enums.BusStatus status);
}
