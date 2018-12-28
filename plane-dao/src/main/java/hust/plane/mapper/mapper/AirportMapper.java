package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.Airport;

import java.util.List;

public interface AirportMapper {

    List<Airport> getAllAirport();

    int insertAirport(Airport airport);

}
