package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    
    List<Restaurante> todos();
    Restaurante porId(Long id);
    void adicionar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}
