package core.db.types;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String zipcode;
	private String country;
	private String street;


	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


	@Override
	public String toString() {
		return "Address{" +
				"zipcode='" + zipcode + '\'' +
				", country='" + country + '\'' +
				", street='" + street + '\'' +
				'}';
	}
}
