package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
@Table(name = "Workshop")
public class Workshop implements Serializable, Comparable<Workshop> {

    private static final long serialVersionUID = 7966771392650078457L;	

    @Id
	private String id;

    private String organizationID;
	private String title;
	private String description;
    private String instructorName;
    private Number numberOfPosting;
    private Boolean certificate;
    private Number duration;
    private String mode;
    private String sector;
	private LocalDateTime postedOn = LocalDateTime.now();



    @PrePersist
	private void prePresist() {
	    setId(UUID.randomUUID().toString());
	}
	
	@Override
	public int compareTo(Workshop other) {
		String key = this.getTitle() + this.getDescription() + this.getOrganizationID();
		String otherKey = other.getTitle() + other.getDescription() + other.getOrganizationID();
		
		return otherKey.compareTo(key);
	}
}
