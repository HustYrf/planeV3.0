package hust.plane.service.impl;

import hust.plane.mapper.mapper.AirportMapper;
import hust.plane.mapper.pojo.Airport;
import hust.plane.service.interFace.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportMapper airportMapper;

    public List<Airport> getAllAirport() {

        List<Airport> airports = airportMapper.getAllAirport();
        return airports;

    }
}
