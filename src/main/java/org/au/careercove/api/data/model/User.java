package org.au.careercove.api.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
	
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;

	@Column(name = "password")
	private String password;
	@Column(name = "mobileNumber")
	private String mobileNumber;

	// TODO: Handle Image URL
	@Column(name = "pfp")
	private String profileImageURL = "";
	@Column(name = "email")
	private String email;

	@Column(name = "user")
	private String type = "USER";

	@Column(name ="gender")
	private String gender;

	@Column(name ="isActive")
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
