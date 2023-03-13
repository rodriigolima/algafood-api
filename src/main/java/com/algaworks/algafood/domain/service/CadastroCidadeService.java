package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    
    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe cadastro de estado com código %d";
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private CadastroEstadoService cadastroEstado;
    
    public Cidade    salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscarOrFalhar(estadoId);
        cidade.setEstado(estado);
        return  cidadeRepository.save(cidade);
    }
    
    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }
    
    public Cidade buscarOrFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}
