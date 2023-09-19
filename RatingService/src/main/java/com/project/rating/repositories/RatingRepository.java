package com.project.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);

}
