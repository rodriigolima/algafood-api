package com.algaworks.algafood.client;

import com.algaworks.algafood.client.api.ClientApiException;
import com.algaworks.algafood.client.api.RestauranteClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {
    public static void main(String[] args) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            RestauranteClient restauranteClient = new RestauranteClient(
                    restTemplate, "http://localhost:8080");

            restauranteClient.listar()
                    .forEach(System.out::println);

        } catch (ClientApiException ex) {
            if (ex.getProblem() != null) {
                System.out.println(ex.getProblem().getUserMessage());
            } else {
                System.out.println("Erro desconhecido");
                ex.printStackTrace();
            }
        }

    }
}
