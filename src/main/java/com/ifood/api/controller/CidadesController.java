package com.ifood.api.controller;

import com.ifood.domain.model.Cidade;
import com.ifood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadesController {

    @Autowired
    private CadastroCidadeService service;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }
}
