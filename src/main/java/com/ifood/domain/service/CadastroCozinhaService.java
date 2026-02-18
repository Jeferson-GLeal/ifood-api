package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cozinha;
import com.ifood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> listar(){
        return repository.findAll();
    }

    public Cozinha buscar(Long id) {
        Optional<Cozinha> cozinha = repository.findById(id);
        return cozinha.get();
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Não existe um cadastro de cozinha com código %d", id)
                        ));

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
