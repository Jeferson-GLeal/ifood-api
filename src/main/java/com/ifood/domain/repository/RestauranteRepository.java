package com.ifood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.ifood.domain.model.Cozinha;
import com.ifood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContaining(String nome, Cozinha cozinhaId);
}
