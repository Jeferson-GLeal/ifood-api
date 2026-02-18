package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Estado;
import com.ifood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroEstadosService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> listar() {
        return repository.findAll();
    }

    public Estado buscar(Long id) {
        Optional<Estado> estado = repository.findById(id);
        return estado.get();
    }

    public Estado salvar(Estado estado) {
        return repository.save(estado);
    }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Cacastro de Estado com o codigo %d inexistente!", id)
                        ));

        try {
            repository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de codigo %d nao pode ser removido, pois estas em uso!", id));
        }
    }

}
