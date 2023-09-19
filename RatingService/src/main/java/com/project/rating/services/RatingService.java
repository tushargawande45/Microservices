package com.project.rating.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.rating.entities.Rating;

@Service
public interface RatingService {
	
	//create
	Rating create(Rating rating);
	
	//GetAllRatings
	List<Rating> getAllRatings();
	
	//GetRatingByUserId
	List<Rating> getRatingByUserId(String userId); 
	
	//GetRatingByHotelId
	List<Rating> getRatingByHotelId(String hotelId);

}
