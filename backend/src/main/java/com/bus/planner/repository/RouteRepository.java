package com.bus.planner.repository;
import com.bus.planner.entity.Route; import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RouteRepository extends JpaRepository<Route,Integer> { List<Route> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source,String destination); }
