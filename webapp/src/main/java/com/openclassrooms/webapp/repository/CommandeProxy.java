package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Commande;

@Component
public class CommandeProxy {

	@Autowired
	private CustomProperties props;

	public Iterable<Commande> getCommandes() {
		String baseApiUrl = props.getApiUrl();
		String getCommandesUrl = baseApiUrl + "/commandes";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Commande>> responseEntity = restTemplate.exchange(
				getCommandesUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Commande>>() {}
				);

		return responseEntity.getBody();
	}





}