package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "WORKSHOP")
public class Workshop implements Serializable, Comparable<Workshop> {

    private static final long serialVersionUID = 7966771392650078457L;	

    @Id
	private String id;

    private String organizationID;
	private String title;
	@Column(length=1000)
	private String description;
    private String instructorName;
    private int numberOfPosting;
    private Boolean certificate;
    private int duration;
	private String type = "Workshop";
    private String mode;
    private String sector;
	private LocalDateTime postedOn;

	@JsonIgnore
	private String skillsSerialized = null;

	@Transient
	private ArrayList<String> skills = new ArrayList<String>();

    @PrePersist
	private void prePresist() {
	    if (id == null || id.trim().length() == 0) {
	    	setId(UUID.randomUUID().toString());
			postedOn = LocalDateTime.now();
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
	
	@Override
	public int compareTo(Workshop other) {
		String key = this.getTitle() + this.getDescription() + this.getOrganizationID();
		String otherKey = other.getTitle() + other.getDescription() + other.getOrganizationID();
		
		return otherKey.compareTo(key);
	}
}
