package com.skdamoda.rbac.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skdamoda.rbac.exception.ResourceNotFoundException;
import com.skdamoda.rbac.model.Privilege;
import com.skdamoda.rbac.model.Role;
import com.skdamoda.rbac.repository.PrivilegeRepository;
import com.skdamoda.rbac.repository.RoleRepository;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@GetMapping("/roles")
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId)
			throws ResourceNotFoundException {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
		return ResponseEntity.ok().body(role);
	}

	@PostMapping("/roles")
	public Role createRole(@Valid @RequestBody Role role) throws ResourceNotFoundException {
		Set<Privilege> privileges= new HashSet<>(role.getPrivileges());
		if(privileges!=null && privileges.size()!=0){
			for(Privilege privilege:privileges){
				Privilege privilegeDetails = privilegeRepository.findById(privilege.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Privilege not found for this id :: " + privilege.getId()));
			role.addPrivilege(privilegeDetails);
			}
		}
		return roleRepository.save(role);
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable(value = "id") Long roleId,
			@Valid @RequestBody Role roleDetails) throws ResourceNotFoundException {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));

		role.setName(roleDetails.getName());
		role.setDescription(roleDetails.getDescription());
		final Role updatedRole = roleRepository.save(role);
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping("/roles/{id}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "id") Long roleId)
			throws ResourceNotFoundException {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));

		roleRepository.delete(role);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
