package org.au.careercove.api.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode()
@ApiIgnore
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

	private boolean isActive = true;

	@PrePersist
	private void prePresist() {
	    setId(UUID.randomUUID().toString());
	}
	
	@Override
	public int compareTo(User other) {
		return other.getFirstName().compareTo(this.getFirstName());
	}
	
}
