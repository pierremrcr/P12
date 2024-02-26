package com.openclassrooms.webapp.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Utilisateur;

@Component
public class UtilisateurProxy {

	@Autowired
	private CustomProperties props;


	public Utilisateur login(String email, String password) {

		String baseApiUrl = props.getApiUrl();
		String loginUrl = baseApiUrl + "/login";
		
		RestTemplate restTemplate = new RestTemplate();

		LoginRequest loginRequest = new LoginRequest(email, password);
        ResponseEntity<Utilisateur> response = restTemplate.postForEntity(loginUrl, loginRequest, Utilisateur.class);
        
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
		
	}

	public Utilisateur createUtilisateur(Utilisateur u) {
		String baseApiUrl = props.getApiUrl();
		String createUtilisateursUrl = baseApiUrl + "/utilisateur";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Utilisateur> request = new HttpEntity<Utilisateur>(u);
		ResponseEntity<Utilisateur> response = restTemplate.exchange(
				createUtilisateursUrl,
				HttpMethod.POST,
				request,
				Utilisateur.class);

		return response.getBody();
	}

	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		String baseApiUrl = props.getApiUrl();
		String updateUtilisateurUrl = baseApiUrl + "/utilisateur/" + utilisateur.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Utilisateur> request = new HttpEntity<Utilisateur>(utilisateur);
		ResponseEntity<Utilisateur> response = restTemplate.exchange(
				updateUtilisateurUrl, 
				HttpMethod.PUT, 
				request, 
				Utilisateur.class);

		return response.getBody();
	}

	public static class LoginRequest {
		private String email;
		private String password;


		public LoginRequest(String email, String password) {
			this.email = email;
			this.password = password;
		}


	}

}
