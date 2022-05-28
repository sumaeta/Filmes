package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.FilmeDTO;
import com.example.demo.exception.EntidadeNaoEncontrada;
import com.example.demo.modal.Filme;
import com.example.demo.repository.FilmeRepository;
import com.example.demo.response.FilmeResponse;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;
	
	@Transactional
	public FilmeResponse salvar(Filme obj) {
		obj.setId(null);
		Filme filme = repository.save(obj);
		FilmeResponse retorno = fromRetorno(filme);
		return retorno;
	}

	@Transactional(readOnly = true) 
	public Page<Filme> listar(Pageable pageable){
		Page<Filme> filmes = repository.findAll(pageable);
		return filmes;
		
	}
	 
	

	@Transactional(readOnly = true)
	public FilmeDTO buscarPorId(Long id) {
		Optional<Filme> obj = repository.findById(id);
		
		if(obj.isEmpty()) {
			throw new EntidadeNaoEncontrada(String.format("Não existe cadastro de cozinha com o id %d", id));
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
	
	private FilmeResponse fromRetorno(Filme filme) {
		FilmeResponse retorno = new FilmeResponse();
		retorno.setAutor(filme.getAutor());
		retorno.setDescricao(filme.getDescricao());
		retorno.setTitulo(filme.getTitulo());
		retorno.setImage(filme.getImage());
		return retorno;
	}
}
