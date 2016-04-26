package com.formation.computerdatabase.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.util.DateFormatter;

/**
 * The Class Computer.
 */

@Entity
@Table(name = "computer")
public class Computer {
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** The name. */
	private String name;
	
	/** The introduced. */
	private LocalDate introduced;
	
	/** The discontinued. */
	private LocalDate discontinued;
	
	/** The company. */
	@ManyToOne
	private Company company;
	
	public Computer() {
	}
	public Computer(String name) {
		this.name = name;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	
	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
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
	
	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public LocalDate getIntroduced() {
		return introduced;
	}
	
	/**
	 * Sets the introduced.
	 *
	 * @param introduced the new introduced
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	
	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	
	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1; 
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		 result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		 result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
		Computer c = (Computer) obj;
		if (this.id == c.getId()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static class Builder {
		private Computer c;

		public Builder(String name) {
			c = new Computer(name);
		}

		public Builder id(Long id) {
			c.id = id;
			return this;
		}

		public Builder name(String name) {
			c.name = name;
			return this;
		}

		public Builder introduced(String introduced) {
			if (!introduced.equals("")) {
				try {
					c.introduced = LocalDate.parse(introduced,  DateTimeFormatter.ofPattern(DateFormatter.getDatePattern()));
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("Wrong date format was provided.", e);
				}
			}
			return this;
		}

		public Builder introduced(LocalDate introduced) {
			c.introduced = introduced;
			return this;
		}

		public Builder discontinued(String discontinued) {
			if (!discontinued.equals("")) {
				try {
					c.discontinued = LocalDate.parse(discontinued,  DateTimeFormatter.ofPattern(DateFormatter.getDatePattern()));
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("Wrong date format was provided.", e);
				}
			}
			return this;
		}

		public Builder discontinued(LocalDate discontinued) {
			c.discontinued = discontinued;
			return this;
		}

		public Builder company(Company company) {
			c.company = company;
			return this;
		}

		public Computer build() {
			return c;
		}
	}
	
	
	
	
	
}
