package core.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@NamedQueries({
		@NamedQuery(name="user.byEmailAndPassword", query="from User where email=:email and password=:password"),
		@NamedQuery(name="user.byEmail", query="from User where email=:email")
})
@Entity
@Table(name="user")
public class User  implements Serializable {

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
	private Date contractEnd;

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

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
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
				", contractEnd=" + contractEnd +
				", projectId=" + projectId +
				'}';
	}
}
