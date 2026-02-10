package com.ifood.api.controller;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Restaurante;
import com.ifood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Restaurante buscar(@PathVariable Long id) {
        Restaurante restaurante = cadastro.buscar(id);
        return restaurante;
    }

    @PostMapping
    public void adicionar(@RequestBody Restaurante restaurante) {
        cadastro.adicionar(restaurante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = cadastro.buscar(id);
        if (restauranteAtual != null) {
            BeanUtils.copyProperties(restaurante, restauranteAtual);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
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
