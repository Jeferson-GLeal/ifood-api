package com.ifood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cozinha;
import com.ifood.domain.model.Restaurante;
import com.ifood.domain.repository.CozinhaRepository;
import com.ifood.domain.service.CadastroRestauranteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastro;

    @GetMapping
    public List<Restaurante> listar() {
        return cadastro.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = cadastro.buscar(id);
        if (restaurante == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {

        try {
            restaurante = cadastro.adicionar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = cadastro.buscar(id);
        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        cadastro.adicionar(restauranteAtual);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = cadastro.buscar(id);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        Merge(campos, restauranteAtual);

        return atualizar(id, restauranteAtual);
    }

    private static void Merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        System.out.println(restauranteOrigem);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                    System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
                    ReflectionUtils.setField(field, restauranteDestino, novoValor);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurante> excluir(@PathVariable long id) {

        try {
            cadastro.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
