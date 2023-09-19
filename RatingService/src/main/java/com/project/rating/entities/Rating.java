package com.project.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_ratings")
public class Rating {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ratingId;
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
	
	
	

}
