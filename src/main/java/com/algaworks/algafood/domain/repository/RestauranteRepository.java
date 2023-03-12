package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, 
        JpaSpecificationExecutor<Restaurante> {

    // Errata: se um restaurante não tiver nenhuma forma de pagamento associada a ele,
    // esse restaurante não será retornado usando JOIN FETCH r.formasPagamento.
    // Para resolver isso, temos que usar LEFT JOIN FETCH r.formasPagamento
//	@Query("from Restaurante r join fetch r.cozinha join fetch r.formasPagamento")
    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();
    
    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
    
//    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
    
    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
    
    Optional<Restaurante> findFirstByNomeContaining(String nome);
    
    List<Restaurante> findTop2ByNomeContaining(String nome);
    
    int countByCozinhaId(Long cozinhaId);
    
}
