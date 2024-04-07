package com.skdamoda.rbac.model;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "privileges")
public class Privilege {

	private long id;
	private String privilegeName;
	private String privilegeDescription;
	private String action;
	private String resource;
	@JsonIgnore
	private Set<Role> roles;
	
	public Privilege() {
		
	}
	
	public Privilege(String privilegeName, String privilegeDescription, String action, String resource) {
		this.privilegeName = privilegeName;
		this.privilegeDescription = privilegeDescription;
		this.action = action;
		this.resource = resource;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "privilege_name", nullable = false)
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	
	@Column(name = "privilege_description", nullable = false)
	public String getPrivilegeDescription() {
		return privilegeDescription;
	}
	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}
	@Column(name = "action", nullable = false)
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Column(name = "resource", nullable = false)
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}

	@ManyToMany(mappedBy = "privileges")
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", privilegeName=" + privilegeName + ", privilegeDescription=" + privilegeDescription +  "]";
	}

	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Privilege))
            return false;
 
        final Privilege privilege = (Privilege)object;
 
        if (id != 0 && privilege.getId() != 0) {
            return id==privilege.getId();
        }
        return false;
    }

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
}
