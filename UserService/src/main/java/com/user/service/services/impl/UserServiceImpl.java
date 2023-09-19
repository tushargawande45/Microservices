package com.user.service.services.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
//import com.user.service.external.services.HotelService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import com.user.service.services.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	//Generate Unique User ID
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		
		//Get User From Database with the help of UserRepository
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not found on server !!" + userId));
		
		//fetch the rating of above user from RATING-SERVICE
		//http://localhost:8083/ratings/users/7ca62054-b386-4a98-a7ec-c1f02176d9de
		
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" +user.getUserId() , Rating[].class);
		logger.info("{} ",ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
				
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
		//Api call to hotelService to get hotel
		//http://localhost:8082/hotels/44faad5e-efcc-4e84-bc49-19450c8612f7
			
		System.out.println(rating.getHotelId());
		
		ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
		Hotel hotel = forEntity.getBody();
		
		//Hotel hotel = hotelService.getHotel(rating.getHotelId());
		
		logger.info("Response status Code: {} ",forEntity.getStatusCode());
		
		//Set The Hotel To rating
		rating.setHotel(hotel);
		
		//Return rating
		return rating;
		
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
		
		
	}
	
}