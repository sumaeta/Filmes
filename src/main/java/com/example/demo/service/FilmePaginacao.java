package com.example.demo.service;

import com.example.demo.modal.Filme;

public class FilmePaginacao {

	private String titulo;
	
	private String image;


	public FilmePaginacao() {
	}

	public FilmePaginacao(Filme filme) {
		this.titulo = filme.getTitulo();
		this.image = filme.getImage();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
