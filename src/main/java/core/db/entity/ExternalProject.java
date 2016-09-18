package core.db.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("EXTERNAL_PROJECT")
@Entity
public class ExternalProject extends Project {

	@Column
	private String forExternal;

	public String getForExternal() {
		return forExternal;
	}

	public void setForExternal(String forExternal) {
		this.forExternal = forExternal;
	}
}
