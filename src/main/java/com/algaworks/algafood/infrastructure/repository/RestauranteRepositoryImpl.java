package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> todos() {
        return entityManager.createQuery("from Restaurante", Restaurante.class).getResultList();

    }

    @Override
    public Restaurante porId(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public void adicionar(Restaurante restaurante) {
        entityManager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Restaurante restaurante) {
        restaurante = porId(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
