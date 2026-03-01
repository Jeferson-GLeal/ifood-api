package com.ifood.api.controller;

import com.ifood.domain.model.Cozinha;
import com.ifood.domain.model.Restaurante;
import com.ifood.domain.repository.CozinhaRepository;
import com.ifood.domain.repository.RestauranteRepository;
import com.ifood.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.ifood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    @Autowired
    private RestauranteRepository repository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(String nome) {
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhaPorNome(String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/cozinhas/exists")
    public boolean cozinhaExistePorNome(String nome) {
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantePorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return repository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantePorNome(String nome, Long cozinhaId) {
        return repository.consultaPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/primeiro-nome")
    public Optional<Restaurante> restaurantePorPrimeiroNome(String nome) {
        return repository.findFirstRestauranteByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/top2-nome")
    public List<Restaurante> restauranteTop2Nome(String nome) {
        return repository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/count")
    public int restaurantCountCozinha(Long cozinhaId) {
        return repository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNomeFrete(String nome,
                                                      BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return repository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        return repository.findAll(comFreteGratis.and(comNomeSemelhante));
    }
}