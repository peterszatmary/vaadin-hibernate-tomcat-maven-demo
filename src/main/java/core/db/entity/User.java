package core.db.entity;

import core.db.types.Address;
import core.db.types.ContractType;
import core.db.types.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
		@NamedQuery(name="user.byEmailAndPassword", query="from User where email=:email and password=:password"),
		@NamedQuery(name="user.byEmail", query="from User where email=:email")
})
@Entity
@Table(name="user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="status")
	private Integer status;

	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="email")
	private String email;

	@Column(name="contract_type")
	@Enumerated(EnumType.STRING) // in db
	private ContractType contractType;

	@Column(name="contract_start")
	// you can use java.util.Date and java.util.Calendar to store data and then have several representations of
	// it, such as a date, an hour, or milliseconds.
	@Temporal(TemporalType.TIMESTAMP) // in db
	private Date contractStart;

	@Column(name="contract_end")
	@Temporal(TemporalType.TIMESTAMP) // in db
	private Date contractEnd;

	// all Address attributes will be in User table, composition, address alone is not persisted.
	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	@ManyToOne(cascade = {CascadeType.ALL}) // to save office to db together with saving user
	@JoinColumn(name= "office_fk")
	private Office office;


	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public Long getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", status=" + status +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", contractType=" + contractType +
				", contractStart=" + contractStart +
				", contractEnd=" + contractEnd +
				", address=" + address +
				", userType=" + userType +
				", office=" + office +
				'}';
	}
}
