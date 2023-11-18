package com.algaworks.algafood.client.api;

import com.algaworks.algafood.client.model.RestauranteResumoModel;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestauranteClient {

    private static final String RESOURCE_PATH = "/restaurantes";

    private RestTemplate restTemplate;
    private String url;

    public RestauranteClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public List<RestauranteResumoModel> listar() {
        URI resourceUri = URI.create(url + RESOURCE_PATH);

        RestauranteResumoModel[] restaurantes = restTemplate
                .getForObject(resourceUri, RestauranteResumoModel[].class);

        return Arrays.asList(restaurantes);
    }
}
