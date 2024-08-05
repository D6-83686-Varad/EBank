package com.app.entity.customer;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.app.entity.base.BaseEntity;
import com.app.entity.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Customer extends BaseEntity {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long customerId;
	    @Column(length=20,nullable = false)
	    private String firstName;
	    @Column(length=20,nullable = false)
	    private String middleName;
	    @Column(length=20,nullable = false)
	    private String lastName;
	    @Column(nullable = false, unique = true)
	    private String email;
	    @Column(length=10,nullable = false, unique = true)
	    private String phoneNumber;
	    @Column(nullable = false)
	    private LocalDate dateOfBirth;
	    @Enumerated(EnumType.STRING)
	    private Gender gender;
	    @Column(length=12,nullable = false)
	    private String adharNo;
	    @Column(length=10)
	    private String panNo;
	    @Lob
		private byte[] profilePhoto;
	    @Column(nullable = false)
	    private boolean status;
}
