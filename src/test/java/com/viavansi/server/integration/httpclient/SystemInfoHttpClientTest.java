package com.viavansi.server.integration.httpclient;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = "debug=true")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SystemInfoHttpClientTest {

	@LocalServerPort
	private int port;

	private String baseUrl="http://localhost";
	private String appId="test_app_id";
	
	@Test
	public void userCanAccessPrivateSystemInfoWithAdminCredentialsUsingGET(){
		accessSystemInfo("adminClientId","adminSecret","admin","password","/rest/system/admin/info");		
	}
	
	@Test(expected=Exception.class)
	public void userCannotAccessPrivateSystemInfoWithUserCredentialsUsingGET(){
		accessSystemInfo("userClientId","userSecret","user","password","/rest/system/admin/info");		
	}
	
	@Test
	public void userCanAccessPrivateSystemInfoWithUserCredentialsUsingGET(){
		accessSystemInfo("userClientId","userSecret","user","password","/rest/system/public/info");		
	}
		
	@Test(expected=Exception.class)
	public void userCannotAccessPrivateSystemInfoWithInvalidCredentials1UsingGET(){
		accessSystemInfo("adminClientId2","adminSecret","user","password","/rest/system/admin/info");		
	} 
	
	@Test(expected=Exception.class)
	public void userCannotAccessPrivateSystemInfoWithInvalidCredentials2UsingGET(){
		accessSystemInfo("adminClientId","adminSecretId2","user","password","/rest/system/admin/info");		
	} 
	
	
	public void accessSystemInfo(String clientId,String clientSecret,String user,String password,String url) {

		// Load the properties file

		Properties config = new Properties();
		config.setProperty(OAuthConstants.GRANT_TYPE, "password");
		config.setProperty(OAuthConstants.USERNAME, user);
		config.setProperty(OAuthConstants.PASSWORD, password);
		config.setProperty(OAuthConstants.CLIENT_ID, clientId);
		config.setProperty(OAuthConstants.CLIENT_SECRET, clientSecret);
		config.setProperty(OAuthConstants.AUTHENTICATION_SERVER_URL, baseUrl+":"+port+"/oauth/token");

		// Generate the OAuthDetails bean from the config properties file
		OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);

		// Validate Input
		if (!OAuthUtils.isValidInput(oauthDetails)) {
			Assert.fail("Please provide valid config properties to continue.");
		}

		// Determine operation
		if (oauthDetails.isAccessTokenRequest()) {
			// Generate new Access token
			String accessToken = OAuthUtils.getAccessToken(oauthDetails);			
			if (OAuthUtils.isValid(accessToken)) {
				oauthDetails.setAccessToken(accessToken);	
				// MAKE CALL
				
				oauthDetails.setResourceServerUrl(baseUrl+":"+port+url);
				OAuthUtils.getProtectedResource(oauthDetails);
				
				
			} else {
				Assert.fail("Could not generate Access token for client_credentials grant_type");
			}
		}
	}
}
