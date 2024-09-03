package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "OrgName")
	private String organizationName;

	@Column(name = "Openings")
	private int numberOfPosting;
	
	@Column(name = "Title")
	private String title;

	@Column(name = "Description", length = 1000)
	private String description;

	@Column(name = "WorkMode")
	private String workMode;

	@Column(name = "Job")
	private String type = "Job";

	@Column(name = "Workhours")
	private String workHours;

	@Column(name = "postedOn")
	private LocalDateTime postedOn;

	@Column(name = "isActive")
	private boolean isActive = true;

	@JsonIgnore
	private String skillsSerialized;

	@Transient
	private ArrayList<String> skills = new ArrayList<String>();
	
	private String sector;

	@PrePersist
	private void prePresist() {
		if (id == null || id.trim().length() == 0) {
	    	setId(UUID.randomUUID().toString());
		}

		
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
	public int compareTo(Job other) {
		String key = this.getTitle() + this.getDescription() + this.getOrganizationID();
		String otherKey = other.getTitle() + other.getDescription() + other.getOrganizationID();
		
		return otherKey.compareTo(key);
	}
	
}
