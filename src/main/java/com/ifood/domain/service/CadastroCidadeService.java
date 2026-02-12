package com.ifood.domain.service;

import com.ifood.domain.model.Cidade;
import com.ifood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> listar() {
        return repository.listar();
    }
}
