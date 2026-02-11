package com.ifood.api.controller;

import com.ifood.domain.model.Estado;
import com.ifood.domain.service.CadastroEstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadosController {

    @Autowired
    private CadastroEstadosService cadastro;

    @GetMapping
    public List<Estado> listar() {
        return cadastro.listar();
    }
}
