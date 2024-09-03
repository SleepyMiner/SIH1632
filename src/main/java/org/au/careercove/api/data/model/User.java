package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Data
@EqualsAndHashCode()
@ApiIgnore
@Table(name="USER_TABLE")
public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = 7966771392650078456L;	

	@Id
	private String id;
	

	private String firstName;

	private String lastName;


	private String password;

	private String mobileNumber;

	// TODO: Handle Image URL

	private String profileImageURL = "";
	
	private String email;


	private String type = "USER";

	private String gender;

	@JsonIgnore
	private String skillsSerialized;

	@Transient
	private ArrayList<String> skills = new ArrayList<String>();

	private boolean isActive = true;

	private LocalDateTime createdTime;
	private LocalDateTime lastUpdatedTime;

	@PrePersist
	private void prePresist() {

		if (id == null || id.trim().length() == 0) {
	    	setId(UUID.randomUUID().toString());
			createdTime = LocalDateTime.now();
		}

		lastUpdatedTime = LocalDateTime.now();

		if (skills != null && skills.size() > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			for (String skill : skills) {
				sb.append(skill);
				sb.append("#");
			}
			
			skillsSerialized = sb.toString();
		}
	}

	@PostLoad
	private void postLoad() {

		if (skillsSerialized != null || skillsSerialized.trim().length() > 0) {

			String[] skillArray = skillsSerialized.split("#");

			for (String skill : skillArray) {
				skills.add(skill);
			}
		}			
	}
	
	@Override
	public int compareTo(User other) {
		return other.getFirstName().compareTo(this.getFirstName());
	}
	
}
