package com.skdamoda.rbac.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private Role role;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public User(String firstName, String lastName, String emailId,Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.role=role;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "email_id", nullable = false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		setRole(role,false);
	}

	public void setRole(Role role, boolean add) {
        this.role = role;
        if (role != null && add) {
            role.addUser(this, false);
        }
    }


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}

	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof User))
            return false;
 
        final User user = (User)object;
 
        if (id != 0 && user.getId() != 0) {
            return id==user.getId();
        }
        return false;
    }
	
}
