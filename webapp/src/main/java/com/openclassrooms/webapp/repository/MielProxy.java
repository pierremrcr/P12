package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Miel;

@Component
public class MielProxy {
	
	@Autowired
	private CustomProperties props;
	
	public Miel getMiel(int id) {
	    String baseApiUrl = props.getApiUrl();
	    String getMielUrl = baseApiUrl + "/miel/" + id;

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Miel> response = restTemplate.exchange(
	            getMielUrl, 
	            HttpMethod.GET, 
	            null,
	            Miel.class
	        );
	    
	    return response.getBody();
	}
	
	public Iterable<Miel> getMiels(){
		String baseApiUrl = props.getApiUrl();
		String getMielsUrl = baseApiUrl + "/miels";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Miel>> response = restTemplate.exchange(
				getMielsUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Miel>>() {}
				);
		
		return response.getBody();
		
	}
	
	public Miel createMiel(Miel m) {
		String baseApiUrl = props.getApiUrl();
		String createMielsUrl = baseApiUrl + "/miel";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Miel> request = new HttpEntity<Miel>(m);
		ResponseEntity<Miel> response = restTemplate.exchange(
				createMielsUrl,
				HttpMethod.POST,
				request,
				Miel.class);
		
		return response.getBody();
			
	}
	
	public Miel updateMiel(Miel m) {
	    String baseApiUrl = props.getApiUrl();
	    String updateMielUrl = baseApiUrl + "/miel/" + m.getId();

	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<Miel> request = new HttpEntity<Miel>(m);
	    ResponseEntity<Miel> response = restTemplate.exchange(
	            updateMielUrl, 
	            HttpMethod.PUT, 
	            request, 
	            Miel.class);
	   
	    
	    return response.getBody();
	}
	
	public void deleteMiel(int id) {
	    String baseApiUrl = props.getApiUrl();
	    String deleteMielUrl = baseApiUrl + "/miel/" + id;
	    
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Void> response = restTemplate.exchange(
	            deleteMielUrl, 
	            HttpMethod.DELETE, 
	            null, 
	            Void.class);
	   
	}
	
}