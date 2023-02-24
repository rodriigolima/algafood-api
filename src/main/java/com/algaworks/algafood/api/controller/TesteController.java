package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {
    
    @Autowired
    private CozinhaRepository cozinhaRepository;
    
    @Autowired 
    private RestauranteRepository restauranteRepository;
    
    @GetMapping("/cozinhas/por_nome")
    public List<Cozinha> cozinhasPorNome(String nome) {
        return cozinhaRepository.findByNomeContaining(nome);
    }
    
    @GetMapping("/restaurantes/com_frete_gratis")
    public List<Restaurante> restaurantesComFreteGratisSpec(String nome) {
        return restauranteRepository.findComFreteGratis(nome);
    }
    
    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restaurantePrimeiro() {
        return restauranteRepository.buscarPrimeiro();
    }
    @GetMapping("/cozinhas/primeira")
    public Optional<Cozinha> primeiraCozinha() {
        return cozinhaRepository.buscarPrimeiro();
    }
}
