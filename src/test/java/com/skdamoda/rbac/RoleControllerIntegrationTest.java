package com.skdamoda.rbac;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.skdamoda.rbac.model.Role;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllRoles() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/roles",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetRoleById() {
		Role role = restTemplate.getForObject(getRootUrl() + "/roles/1", Role.class);
		System.out.println(role.getRoleName());
		assertNotNull(role);
	}

	@Test
	public void testCreateRole() {
		Role role = new Role();
		role.setRoleName("admin");
		role.setRoleDescription("Admin has all access");

		ResponseEntity<Role> postResponse = restTemplate.postForEntity(getRootUrl() + "/roles", role, Role.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateRole() {
		int id = 1;
		Role role = restTemplate.getForObject(getRootUrl() + "/roles/" + id, Role.class);
		role.setRoleName("admin1");
		role.setRoleDescription("Admin1 has alla Access");

		restTemplate.put(getRootUrl() + "/roles/" + id, role);

		Role updatedRole = restTemplate.getForObject(getRootUrl() + "/roles/" + id, Role.class);
		assertNotNull(updatedRole);
	}

	@Test
	public void testDeleteRole() {
		int id = 2;
		Role role = restTemplate.getForObject(getRootUrl() + "/roles/" + id, Role.class);
		assertNotNull(role);

		restTemplate.delete(getRootUrl() + "/roles/" + id);

		try {
			role = restTemplate.getForObject(getRootUrl() + "/roles/" + id, Role.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
