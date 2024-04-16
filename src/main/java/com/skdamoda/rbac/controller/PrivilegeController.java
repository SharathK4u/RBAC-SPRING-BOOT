package com.skdamoda.rbac.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

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
import com.skdamoda.rbac.repository.PrivilegeRepository;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1")
public class PrivilegeController {
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@GetMapping("/privileges")
	public List<Privilege> getAllPrivileges() {
		return privilegeRepository.findAll();
	}

	@GetMapping("/privileges/{id}")
	public ResponseEntity<Privilege> getPrivilegeById(@PathVariable(value = "id") Long privilegeId)
			throws ResourceNotFoundException {
		Privilege privilege = privilegeRepository.findById(privilegeId)
				.orElseThrow(() -> new ResourceNotFoundException("Privilege not found for this id :: " + privilegeId));
		return ResponseEntity.ok().body(privilege);
	}

	@PostMapping("/privileges")
	public Privilege createPrivilege(@Valid @RequestBody Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

	@PutMapping("/privileges/{id}")
	public ResponseEntity<Privilege> updatePrivilege(@PathVariable(value = "id") Long privilegeId,
			@Valid @RequestBody Privilege privilegeDetails) throws ResourceNotFoundException {
		Privilege privilege = privilegeRepository.findById(privilegeId)
				.orElseThrow(() -> new ResourceNotFoundException("Privilege not found for this id :: " + privilegeId));

		privilege.setName(privilegeDetails.getName());
		privilege.setDescription(privilegeDetails.getDescription());
		privilege.setAction(privilegeDetails.getAction());
		privilege.setResource(privilegeDetails.getResource());
		final Privilege updatedPrivilege = privilegeRepository.save(privilege);
		return ResponseEntity.ok(updatedPrivilege);
	}

	@DeleteMapping("/privileges/{id}")
	public Map<String, Boolean> deletePrivilege(@PathVariable(value = "id") Long privilegeId)
			throws ResourceNotFoundException {
		Privilege privilege = privilegeRepository.findById(privilegeId)
				.orElseThrow(() -> new ResourceNotFoundException("Privilege not found for this id :: " + privilegeId));

		privilegeRepository.delete(privilege);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
