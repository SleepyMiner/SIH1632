package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Data
@EqualsAndHashCode()
@ApiIgnore
public class Account implements Serializable, Comparable<Account> {

	private static final long serialVersionUID = 7966771392650079456L;	

	@Id
	private String id;
	
	private String organizationName;

	private String address;
	private String website;
	private String contactNumber;
	private String primaryEmail;

	private String type = "ORGANIZATION";

	private boolean isActive = true;

	@PrePersist
	private void prePresist() {
	    setId(UUID.randomUUID().toString());
	}
	
	@Override
	public int compareTo(Account other) {
		return other.getOrganizationName().compareTo(this.getOrganizationName());
	}
	
}
