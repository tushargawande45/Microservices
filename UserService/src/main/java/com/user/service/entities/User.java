package com.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "micro_users")
public class User {
	
	@Id
	@Column(name ="ID")
	private String userId;
	@Column(name ="NAME")
	private String name;
	@Column(name="EMAIL")
	private String email;
	@Column(name ="ABOUT")
	private String about;
	
	@Transient   //use to indicate that it will not be store in database
	List<Rating> ratings = new ArrayList<>();
	
	

}
