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
public class Job implements Serializable, Comparable<Job> {

	private static final long serialVersionUID = 7966771392650078457L;	

	@Id
	private String id;

	private String organizationID;
	
	private String title;
	private String description;

	private String workMode;

	private String type = "Job";

	private String workHours;
	private String postedOn;

	private boolean isActive = true;

	@PrePersist
	private void prePresist() {
	    setId(UUID.randomUUID().toString());
	}
	
	@Override
	public int compareTo(Job other) {
		String key = this.getTitle() + this.getDescription() + this.getOrganizationID();
		String otherKey = other.getTitle() + other.getDescription() + other.getOrganizationID();
		
		return otherKey.compareTo(key);
	}
	
}
