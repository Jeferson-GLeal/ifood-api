package com.ifood.api.controller;

import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Estado;
import com.ifood.domain.service.CadastroEstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadosController {

    @Autowired
    private CadastroEstadosService cadastro;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        List<Estado> estado = cadastro.listar();
        return ResponseEntity.status(HttpStatus.OK).body(estado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {
        Estado estado = cadastro.buscar(id);

        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(estado);
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
        cadastro.adicionar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
