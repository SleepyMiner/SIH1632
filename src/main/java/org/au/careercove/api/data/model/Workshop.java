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
@Table(name = "Workshop")
public class Workshop implements Serializable, Comparable<Workshop> {

    private static final long serialVersionUID = 7966771392650078457L;	

    @Id
	private String id;

    @Column(name = "OrganizationID")
    private String organizationID;
	
    @Column(name = "Title")
	private String title;

    @Column(name = "Description")
	private String description;

    @Column(name = "InstructorName")
    private String instructorName;

    @Column(name = "NumberOfPosting")
    private Number numberOfPosting;

    @Column(name = "Certificate")
    private Boolean certificate;

    @Column(name = "Duration")
    private Number duration;

    @Column(name = "Mode")
    private String mode;

    @Column(name = "Sector")
    private String sector;



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
