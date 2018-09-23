package hust.plane.mapper.mapper;

import java.util.List;

import hust.plane.mapper.pojo.Airport;

public interface AirportMapper {

	List<Airport> getAllAirport();
	
	int insertAirport(Airport airport);
	
}
