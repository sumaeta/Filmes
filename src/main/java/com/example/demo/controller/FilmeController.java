package com.example.demo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.FilmeDTO;
import com.example.demo.modal.Filme;
import com.example.demo.service.FilmeService;


@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

	@Autowired
	private FilmeService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Filme request){
		Filme retorno = service.salvar(request);
		//Quando o objeto for criado, retorna o link no hearders
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(request.getId()).toUri();
		return ResponseEntity.created(uri).body(retorno);
	}
	
	
	 @GetMapping 
	 public ResponseEntity<Page<?>>listar(Pageable pageable){ 
		 Page<Filme> filmes = service.listar(pageable);
		 return ResponseEntity.ok().body(filmes); 
	 }

	 @GetMapping("/detalhes/{id}") 
	 public ResponseEntity<?> verDetalhes(@PathVariable Long id){ 
		 FilmeDTO obj = service.buscarPorId(id);
		 return ResponseEntity.ok().body(obj); 
	}
}
