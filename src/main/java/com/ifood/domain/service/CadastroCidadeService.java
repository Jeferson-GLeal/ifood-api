package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cidade;
import com.ifood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> listar() { return repository.findAll(); }

    public Cidade buscar(Long id) {
        Optional<Cidade> cidade = repository.findById(id);
        return cidade.get();
    }

    public Cidade salvar(Cidade cidade) { return repository.saveAndFlush(cidade); }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("A Cidade de codigo %d nao foi encontrada!", id)
                        ));

        try {
            repository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("A Cidade de codigo %d esta em uso!", id));
        }
    }
}
