//package com.app.loan.entities;
//
//import java.util.ArrayList;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//
//import com.app.loan.idGenerator.StringPrefixedSequenceIdGenerator;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//
///**
// * Represents an account entity in the system.
// * Each account has a unique identifier, a balance, and can have multiple associated requests.
// */
//@Entity
//@Table(name = "account")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(callSuper = true)
//public class Account {
//	
//	
//	/**
//     * Unique identifier for the account, generated using a custom sequence.
//     */
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
//    @GenericGenerator(
//        name = "acc_seq", 
//        strategy = "com.app.loan.idGenerator.StringPrefixedSequenceIdGenerator", 
//        parameters = {
//            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
//            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),
//            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") })
//	@Column(name="account_no")
//	private String accountNo;
//	
//	
//	/**
//     * Current balance of the account.
//     */
//	@Column(name="balance")
//	private int balance;
//	
//	
//	/**
//     * List of requests associated with the account.
//     * The list is managed by the account entity, with cascading and orphan removal enabled.
//     */
//	@JsonManagedReference
//	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Request>request = new ArrayList<>();
//	
//	
//	/**
//     * Adds a request to the account.
//     * Also sets the account reference on the request.
//     * 
//     * @param request The request to be added.
//     */
//	//helper method
//	public void addRequest(Request request) {
//		this.request.add(request);
//		request.setAccount(this);
//	}
//
//	public Account(int balance) {
//		super();
//		this.balance = balance;
//	}
//}