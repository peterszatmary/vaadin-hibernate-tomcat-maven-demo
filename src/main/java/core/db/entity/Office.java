package core.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="office")
public class Office implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "office", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<User> users;



	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Office{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
