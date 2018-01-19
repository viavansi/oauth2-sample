package com.viavansi.server;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.viavansi.server.model.entity.App;


public class TestUtils {

	public static OAuth2RestTemplate createAdminRestTemplateForPort(String baseUrl) {
		return createTemplate(baseUrl,"adminClientId","adminSecret","admin","password","admin");
	}

	public static OAuth2RestTemplate createUserRestTemplateForPort(String baseUrl) {
		return createTemplate(baseUrl,"userClientId","userSecret","user","password","user");
	}
	private static OAuth2RestTemplate createTemplate(String baseUrl,String clientId,String secret,String user,String password,String scope) {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setUsername(user);
		resourceDetails.setPassword(password);
		resourceDetails.setAccessTokenUri(baseUrl + "/oauth/token");
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(secret);
		resourceDetails.setGrantType("password");
		resourceDetails.setScope(Arrays.asList(scope));

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);

		restTemplate.setMessageConverters(Arrays.asList(converter));
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

		return restTemplate;
	}
}
