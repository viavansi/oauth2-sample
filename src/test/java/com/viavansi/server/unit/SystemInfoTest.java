package com.viavansi.server.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.viavansi.server.TestUtils;
import com.viavansi.server.controller.model.SystemInfoResponse;
import com.viavansi.server.controller.model.SystemInfoStatusCode;
import com.viavansi.server.service.SystemInfoService;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = "debug=true")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SystemInfoTest {

	@Autowired
	private SystemInfoService systemService;

	@LocalServerPort
	private int port;

	@Test
	public void userCanAccessPrivateSystemInfoWithAdminCredentials() {
		assertThat(systemService).isNotNull();
		SystemInfoResponse response = systemService.adminSystemInfo();
		assertThat(response).isNotNull();
		assertThat(response.getStatus()).isEqualTo(SystemInfoStatusCode.OK);
	}

	@Test
	public void userCanAccessPrivateSystemInfoWithAdminCredentialsUsingGET() {
		String baseUrl = "http://localhost:" + port;
		OAuth2RestTemplate restTemplate = TestUtils.createAdminRestTemplateForPort(baseUrl);
		SystemInfoResponse response = restTemplate.getForObject(baseUrl + "/rest/system/admin/info",
				SystemInfoResponse.class);
		System.out.println(response.getStatus());
	}
	
	@Test
	public void userCanAccessPublicSystemInfoWithAdminCredentialsUsingGET() {
		String baseUrl = "http://localhost:" + port;
		OAuth2RestTemplate restTemplate = TestUtils.createAdminRestTemplateForPort(baseUrl);
		SystemInfoResponse response = restTemplate.getForObject(baseUrl + "/rest/system/public/info",
				SystemInfoResponse.class);
		System.out.println(response.getStatus());
	}

	@Test(expected = Exception.class)
	public void userCannotAccessPrivateSystemInfoWithUserCredentialsUsingGET() {
		String baseUrl = "http://localhost:" + port;
		OAuth2RestTemplate restTemplate = TestUtils.createUserRestTemplateForPort(baseUrl);
		restTemplate.getForObject(baseUrl + "/rest/system/admin/info", SystemInfoResponse.class);
	}

}
