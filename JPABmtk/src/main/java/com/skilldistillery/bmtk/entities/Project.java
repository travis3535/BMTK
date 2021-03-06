package com.skilldistillery.bmtk.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	
	//Fields
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String name;
		
		private String description;
		

		@ManyToOne
		@JoinColumn(name="customer_id")
		private Customer customer;
		
//		@JsonIgnore
//		@JsonManagedReference(value="projectToCompany")
		@ManyToOne
		@JoinColumn(name="company_id")
		private Company company;
		
		
		@OneToMany(mappedBy="project")
		List<Task> tasks;

		private Boolean active;
		//Constructors
		
		public Project() {
			super();
		}
		

		public Project(int id, String name, Customer customer, Company company, Boolean active) {
			super();
			this.id = id;
			this.name = name;
			this.customer = customer;
			this.company = company;
			this.active = active;
		}


		public Project(int id, String name, String description, Customer customer, Company company, List<Task> tasks, Boolean active) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.customer = customer;
			this.company = company;
			this.tasks = tasks;
			this.active = active;
		}


		public Boolean getActive() {
			return active;
		}


		public void setActive(Boolean active) {
			this.active = active;
		}


		public List<Task> getTasks() {
			return tasks;
		}

		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public Company getCompany() {
			return company;
		}

		public void setCompany(Company comp) {
			this.company = comp;
		}
		
		

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		@Override
		public String toString() {
			return "Project [id=" + id + ", name=" + name + ", customer=" + customer + ", company=" + company
					+ ", active=" + active + "]";
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
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
			Project other = (Project) obj;
			if (id != other.id)
				return false;
			return true;
		}

		
		
		

}
