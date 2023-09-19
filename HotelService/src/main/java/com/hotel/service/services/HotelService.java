package com.hotel.service.services;

import java.util.List;

import com.hotel.service.entities.Hotel;

public interface HotelService {
	
	Hotel create(Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel getById(String id);

}
