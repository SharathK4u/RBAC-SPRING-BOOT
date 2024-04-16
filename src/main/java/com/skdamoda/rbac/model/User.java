package com.skdamoda.rbac.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	private long id;
	private String firstName;
	private String lastName;
	private int age;
	private Role role;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public User(String firstName, String lastName, int age,Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
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
	
	@Column(name = "age", nullable = false)
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
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
