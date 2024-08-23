package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Data
@EqualsAndHashCode()
@ApiIgnore
@Table(name = "Account")
public class Account implements Serializable, Comparable<Account> {

	private static final long serialVersionUID = 7966771392650079456L;	

	@Id
	private String id;
	
	@Column(name = "OrganizationName")
	private String organizationName;

	@Column(name = "Address")
	private String address;
	@Column(name = "Website")
	private String website;
	@Column(name = "ContactNumber")
	private String contactNumber;
	@Column(name = "Email")
	private String primaryEmail;

	@Column(name = "Type")
	private String type = "ORGANIZATION";

	@Column(name = "isActive")
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
