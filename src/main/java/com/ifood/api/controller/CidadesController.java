package com.ifood.api.controller;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cidade;
import com.ifood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadesController {

    @Autowired
    private CadastroCidadeService cadastro;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cadastro.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        Cidade cidade = cadastro.buscar(id);

        if (cidade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cidade);
    }

    @PostMapping
    public void adicionar(@RequestBody Cidade cidade) {
        cadastro.adicionar(cidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cadastro.buscar(id);

        if (cidadeAtual == null) {
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        cadastro.adicionar(cidadeAtual);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cidadeAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cidade> excluir(@PathVariable Long id) {
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
