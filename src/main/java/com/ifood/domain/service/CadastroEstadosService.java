package com.ifood.domain.service;

import com.ifood.domain.model.Estado;
import com.ifood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadosService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() { return estadoRepository.listar(); }

    public Estado buscar(Long id) { return estadoRepository.buscar(id); }
}
