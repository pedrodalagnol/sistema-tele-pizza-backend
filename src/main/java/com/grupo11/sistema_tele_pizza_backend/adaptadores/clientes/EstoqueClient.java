package com.grupo11.sistema_tele_pizza_backend.adaptadores.clientes;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class EstoqueClient {

    private final RestTemplate restTemplate;
    private final String estoqueServiceUrl = "http://stock-service/stock";

    public EstoqueClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> verificaDisponibilidade(Map<String, Integer> ingredientes) {
        String url = estoqueServiceUrl + "/verifica";
        HttpEntity<Map<String, Integer>> request = new HttpEntity<>(ingredientes);
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<List<String>>() {}
        ).getBody();
    }
}
