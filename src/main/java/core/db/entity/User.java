package core.db.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="status")
	private int status;
        
	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="email")
	private String email;

	@Column(name="contract_type")
	private String contractType;

	@Column(name="contract_start")
	private Date contractStart;

	@Column(name="contract_end")
	private Date contract_end;

	@Column(name="project_id")
	private int projectId;


	public User() { }

	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public long getId() {
		return id;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Date getContract_end() {
		return contract_end;
	}

	public void setContract_end(Date contract_end) {
		this.contract_end = contract_end;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", status=" + status +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", contractType='" + contractType + '\'' +
				", contractStart=" + contractStart +
				", contract_end=" + contract_end +
				", projectId=" + projectId +
				'}';
	}
}
