package com.sec.fb.springsecurityfbsso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
public class SpringSecurityFbSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityFbSsoApplication.class, args);
	}

}
