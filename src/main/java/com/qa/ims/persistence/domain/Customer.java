package com.qa.ims.persistence.domain;

import java.util.Objects;

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
		return Objects.hash(forename, id, surname);
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
		return Objects.equals(forename, other.forename) && Objects.equals(id, other.id)
				&& Objects.equals(surname, other.surname);
	}

}
