package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaDTO;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    
    @Autowired
    private CozinhaRepository cozinhaRepository;
    
    @Autowired
    private CadastroCozinhaService cadastroCozinha;
    
    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;
    
    @Autowired
    protected CozinhaModelAssembler cozinhaModelAssembler;
    
    @GetMapping
    public List<CozinhaDTO> listar() { return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll()); }
    
    
    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
        return cozinhaModelAssembler.toModel(cozinha);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));
    }
    
    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual =  cadastroCozinha.buscarOuFalhar(cozinhaId);
        
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
    }
    
    @DeleteMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
    }
}
