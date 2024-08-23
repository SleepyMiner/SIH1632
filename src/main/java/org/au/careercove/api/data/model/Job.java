package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
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

	@Column(name = "OrgID")
	private String organizationID;
	
	@Column(name = "Title")
	private String title;
	@Column(name = "Description")
	private String description;

	@Column(name = "WorkMode")
	private String workMode;

	@Column(name = "Job")
	private String type = "Job";

	@Column(name = "Workhours")
	private String workHours;

	@Column(name = "postedOn")
	private LocalDateTime postedOn = LocalDateTime.now();

	@Column(name = "isActive")
	private boolean isActive = true;
	
	private String sector;

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
