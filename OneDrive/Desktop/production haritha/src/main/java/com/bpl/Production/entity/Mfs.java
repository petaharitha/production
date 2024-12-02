package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mfs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mfs implements Serializable {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "created_by")
	    private Integer createdBy;

	    @Column(name = "last_modified_by")
	    private Integer lastModifiedBy;

	    @Column(name = "created_date", length = 19)
	    private LocalDateTime createdDate;

	    @Column(name = "last_modified_date", length = 19)
	    private LocalDateTime lastModifiedDate;

	    @Column(name = "code")
	    private Integer code;

	    @Column(name = "industry_branch")
	    private String industryBranch;

	    @Column(name = "target_group")
	    private String targetGroup;

	    @Column(name = "applications")
	    private String applications;

	    @Column(name = "further_application")
	    private String furtherApplication;
}
