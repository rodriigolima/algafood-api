package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeDTO;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private CadastroCidadeService cadastroCidade;
    
    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;
    
    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;
    
    @GetMapping
    public List<CidadeDTO> listar() { return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll()); }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId){
        
        Cidade cidade = cadastroCidade.buscarOrFalhar(cidadeId);
        
        return cidadeModelAssembler.toModel(cidade);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO adicionar(@RequestBody @Valid CidadeInput cidadeInput){
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            
            return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
    
    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput){
        try {
            Cidade cidadeAtual =  cadastroCidade.buscarOrFalhar(cidadeId);
            
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            
            return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
        
    }
    
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
        
    }
}
