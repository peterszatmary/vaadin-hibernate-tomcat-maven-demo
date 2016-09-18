package core.db.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("INTERNAL_PROJECT")
@Entity
public class InternalProject extends Project {

	@Column
	private String forInternal;

	public String getForInternal() {
		return forInternal;
	}

	public void setForInternal(String forInternal) {
		this.forInternal = forInternal;
	}
}
