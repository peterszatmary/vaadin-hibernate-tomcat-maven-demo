package core.db.entity;

import javax.persistence.*;

@Entity
@Table(name="office")
public class Office {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name")
	private String name;

	public Office() { }

	public long getId() {
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
