package core.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="office")
public class Office implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name")
	private String name;

	public Office() { }

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
