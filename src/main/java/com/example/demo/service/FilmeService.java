package com.example.demo.service;

import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.FilmeController;
import com.example.demo.dto.FilmeDTO;
import com.example.demo.exception.ConstraintViolationException;
import com.example.demo.exception.EntidadeNaoEncontrada;
import com.example.demo.modal.Filme;
import com.example.demo.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;
	
	@Transactional
	public Filme salvar(Filme obj) {
		obj.setId(null);
		
		try {
			Filme filme = repository.save(obj);
			obj.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FilmeController.class).verDetalhes(obj.getId())).withSelfRel());
			obj.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FilmeController.class).listar(null)).withRel("Filmes"));
			return filme;
		}catch(ServiceException e) {
			throw new ConstraintViolationException("Erro ao Salvar o filme: " + obj.getTitulo());
		}
	}

	@Transactional(readOnly = true) 
	public Page<Filme> listar(Pageable pageable ){
		Page<Filme> filmes = repository.findAll(pageable);
		return filmes;
	}

	@Transactional(readOnly = true)
	public FilmeDTO buscarPorId(Long id) {
		Optional<Filme> obj = repository.findById(id);
		
		if(obj.isEmpty()) {
			throw new EntidadeNaoEncontrada(String.format("Não existe cadastro de Filme com o id %d", id));
		}
		
		FilmeDTO dto = conveter(obj);
		return dto;
	}
	
	private FilmeDTO conveter(Optional<Filme> obj) {
		FilmeDTO retorno = new FilmeDTO();
		retorno.setAutor(obj.get().getAutor());
		retorno.setDescricao(obj.get().getDescricao());
		retorno.setTitulo(obj.get().getTitulo());
		retorno.setImage(obj.get().getImage());
		return retorno;
	}
}
