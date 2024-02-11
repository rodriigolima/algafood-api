package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoDTO;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@GetMapping
	public List<GrupoDTO> listar() {

		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}

	@GetMapping("/{grupoId}")
	public GrupoDTO buscar(@PathVariable Long grupoId) {

		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		return grupoModelAssembler.toModel(grupo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {

		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

		return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupo));
	}

	@PutMapping("/{grupoId}")
	public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {

		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);

		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

		return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupoAtual));

	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {

		cadastroGrupo.excluir(grupoId);
	}
}
