package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    
    List<Cozinha> todas();
    Cozinha porId(Long id);
    void adicionar(Cozinha cozinha);
    void remover(Cozinha cozinha);
}
