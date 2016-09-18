package core.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Inheritance :
 *
 * Single-Table-per-Class Hierarchy : everything in one table, with discriminator. default.
 * Joined-subclass strategy : 2 tables with foreign key. No duplicate attributes/columns in tables. Can lead to poor performance.
 * Table-per-concrete-class strategy : each class has table representation.
 * 		duplication on db level. No need for discriminators.
 *
 *		@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 *		@Id
 *		@GeneratedValue(strategy=GenerationType.TABLE)
 *		private Long id;
 *
 */
@Entity
@Table(name="project")
// Single-Table-per-Class Hierarchy is default so it is no need for using
// @Inheritance annotation in this case
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="discriminator",
discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PROJECT")
public class Project implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name="project_start")
	@Temporal(TemporalType.TIMESTAMP)
	private Date projectStart;

	@Column(name="project_end")
	@Temporal(TemporalType.TIMESTAMP)
	private Date projectEnd;

	@Column(name="successful")
	private Boolean successful;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getProjectStart() {
		return projectStart;
	}

	public void setProjectStart(Date projectStart) {
		this.projectStart = projectStart;
	}

	public Date getProjectEnd() {
		return projectEnd;
	}

	public void setProjectEnd(Date projectEnd) {
		this.projectEnd = projectEnd;
	}

	public Boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Project{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", projectStart=" + projectStart +
				", projectEnd=" + projectEnd +
				", successful=" + successful +
				'}';
	}
}
