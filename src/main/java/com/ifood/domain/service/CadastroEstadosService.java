package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Estado;
import com.ifood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadosService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado buscar(Long id) {
        return estadoRepository.buscar(id);
    }

    public Estado adicionar(Estado estado) {
        return estadoRepository.adicionar(estado);
    }

    public void excluir(Long id) {
        try {
            estadoRepository.remover(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Cacastro de Estado com o codigo %d inexistente!", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de codigo %d nao pode ser removido, pois estas em uso!", id));
        }
    }

}
