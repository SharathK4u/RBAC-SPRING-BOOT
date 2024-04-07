package com.skdamoda.rbac.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	private long id;
	private String roleName;
	private String roleDescription;
	@JsonIgnore
	private List<User> users;
	private Set<Privilege> privileges;

	public Role() {
		
	}
	
	public Role(String roleName, String roleDescription) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	public Role(String roleName, String roleDescription,Set<Privilege> privileges) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.privileges=privileges;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "role_name", nullable = false)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "role_description", nullable = false)
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
	public List<User> getUsers() { return users; }
 
    public void setUsers(List<User> users)
    {
        this.users = users;
    }

	public void addUser(User user) {
        addUser(user, true);
    }
 
    void addUser(User user, boolean set) {
        if (user != null) {
            getUsers().add(user);
            if (set) {
                user.setRole(this, false);
            }
        }
    }

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ROLE_PRIVILEGE", joinColumns = @JoinColumn(name = "role_id"), 
        	inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public void addPrivilege(Privilege privilege) {
		if(this.privileges.contains(privilege)){
			this.privileges.remove(privilege);
		}
		this.privileges.add(privilege);
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDescription=" + roleDescription +  "]";
	}

	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Role))
            return false;
 
        final Role role = (Role)object;
 
        if (id != 0 && role.getId() != 0) {
            return id==role.getId();
        }
        return false;
    }

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
}
