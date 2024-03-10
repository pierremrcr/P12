package com.openclassrooms.webapp.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
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
	    HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest);

	    try {
	        ResponseEntity<Utilisateur> response = restTemplate.exchange(
	                loginUrl,
	                HttpMethod.POST,
	                request,
	                Utilisateur.class);

	        if (response.getStatusCode().is2xxSuccessful()) {
	            return response.getBody();
	        } else {
	            return null;
	        }
	    } catch (HttpClientErrorException e) {
	        if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	            System.err.println("Échec de l'authentification : Accès refusé");
	        } else {
	            System.err.println("Erreur client : " + e.getStatusCode());
	        }
	        return null;
	    } catch (HttpServerErrorException e) {
	        System.err.println("Erreur serveur : " + e.getStatusCode());
	        return null;
	    } catch (RestClientException e) {
	        System.err.println("Erreur lors de la communication avec l'API : " + e.getMessage());
	        return null;
	    }
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
	
	public Utilisateur findUtilisateurById(Long id) {
	    String baseApiUrl = props.getApiUrl();
	    String findByIdUrl = baseApiUrl + "/utilisateur/" + id;

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Utilisateur> response = restTemplate.getForEntity(findByIdUrl, Utilisateur.class);

	    return response.getBody();
	}


	public void deleteUtilisateurById(Long id) {
	    String baseApiUrl = props.getApiUrl();
	    String deleteUrl = baseApiUrl + "/utilisateur/" + id;

	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.exchange(
	        deleteUrl,
	        HttpMethod.DELETE,
	        null,
	        Void.class); 
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
		
	    private String adresseMail; 
	    private String motDePasse;

        
        public LoginRequest() {
        }
        

		public LoginRequest(String adresseMail, String motDePasse) {
			this.adresseMail = adresseMail;
			this.motDePasse = motDePasse;
		}




		public String getAdresseMail() {
			return adresseMail;
		}


		public void setAdresseMail(String adresseMail) {
			this.adresseMail = adresseMail;
		}


		public String getMotDePasse() {
			return motDePasse;
		}


		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}
		


	}


}
