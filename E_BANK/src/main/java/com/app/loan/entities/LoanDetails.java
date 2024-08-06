package com.app.loan.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)

	
public class LoanDetails extends BaseEntity{
		
		
		@Id
		@Column(name="loan_name", nullable = false, unique = true)
		private String loanName;
		
		@Column(name="interest_rate", nullable = false)
		private float interestRate;
		
		@Column(name="tenure", nullable = false)
		private int tenure;
		
		@Column(name="max_amount", nullable = false)
		private int maxAmount;
		
		@Column(name="min_amount", nullable = false)
		private int minAmount;
		
		@JsonIgnore
		@OneToMany(mappedBy = "details", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Request>requests = new ArrayList<>();
		//helper method
		public void addRequestLoanType(Request requests) {
			this.requests.add(requests);
			requests.setDetails(this);
		}
		public LoanDetails(float interestRate, int tenure, int maxAmount, int minAmount, List<Request> requests) {
			super();
			this.interestRate = interestRate;
			this.tenure = tenure;
			this.maxAmount = maxAmount;
			this.minAmount = minAmount;
			this.requests = requests;
		}
		
	}
	

