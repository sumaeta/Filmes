package com.example.demo.dto;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.modal.Filme;

public class FilmeDTO extends RepresentationModel<Filme> {
	
	private String autor;
	private String titulo;
	private String descricao;
	private String image;
	
	public FilmeDTO() {
	}

	public FilmeDTO(Filme obj) {
		this.autor = obj.getAutor();
		this.titulo = obj.getTitulo();
		this.descricao = obj.getDescricao();
		this.image = obj.getImage();
	}

	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
