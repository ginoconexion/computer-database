package com.formation.computerdatabase.model;

import javax.validation.constraints.Size;

/**
 * The Class Company.
 */
public class Company {
	
	/** The id. */
	private long id;
	
	
	/** The name. */
	@Size(min=2, max=25)
	private String name;
	
	public Company(String name) {
		this.name = name;
	}
	public Company() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1; 
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		else if (obj == null) {
			return false;
		}
		else if (getClass() != this.getClass()) {
			return false;
		}
		Company c = (Company) obj;
		if (this.id == c.getId()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static class Builder {
		private Company c;

		public Builder(String name) {
			c = new Company(name);
		}

		public Builder id(Long id) {
			c.id = id;
			return this;
		}

		public Builder name(String name) {
			c.name = name;
			return this;
		}

		public Company build() {
			return c;
		}
	}
	
	
	
}
