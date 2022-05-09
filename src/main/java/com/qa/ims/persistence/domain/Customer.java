package com.qa.ims.persistence.domain;

public class Customer {

	private Long id;
	private String forename;
	private String surname;

	public Customer(String forename, String surname) {
		this.setForename(forename);
		this.setSurname(surname);
	}

	public Customer(Long id, String forename, String surname) {
		this.setId(id);
		this.setForename(forename);
		this.setSurname(surname);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "id:" + id + " forename:" + forename + " surname:" + surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((forename == null) ? 0 : forename.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (getForename() == null) {
			if (other.getForename() != null)
				return false;
		} else if (!getForename().equals(other.getForename()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
