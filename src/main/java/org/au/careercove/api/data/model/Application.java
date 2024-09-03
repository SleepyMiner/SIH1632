package org.au.careercove.api.data.model;

import java.io.Serializable;
import java.util.UUID;
import java.time.LocalDateTime;

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
@Table(name = "APPLICATION")
public class Application implements Serializable, Comparable<Application>{
    
    private static final long serialVersionUID = 7966771392650078457L;	

    @Id
	private String id;

    private String applicantName;
    private String contactDetails;
    private String type;
    private String experience;
    private String companyName;


    private LocalDateTime createdData = LocalDateTime.now();

    @PrePersist
	private void prePresist() {
	    setId(UUID.randomUUID().toString());
	}
	
	@Override
	public int compareTo(Application other) {
		return this.id.compareTo(other.id);
	}
}
