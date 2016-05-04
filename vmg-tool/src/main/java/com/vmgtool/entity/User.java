package com.vmgtool.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 5, message="Username must be at least 5 characters!")
	@Column(unique=true)
	private String name;
	@Email(message="Please insert a valid email")
	private String email;
	@Size(min = 5, message="Password must be at least 5 characters!")
	private String password;
	@ManyToMany
	@JoinTable
	private List<Role> roles;
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	private List<Studies> studies;
	private boolean enabled;
	
	private String rolesDescription;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Studies> getStudies() {
		return studies;
	}

	public void setStudies(List<Studies> studies) {
		this.studies = studies;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRolesDescription() {
		if(this.getRoles() != null){
			for(Role role : roles){
				rolesDescription+=role+" ";
			}
		}
		return rolesDescription;
	}

	public void setRolesDescription(String rolesDescription) {
		this.rolesDescription = rolesDescription;
	}

}
