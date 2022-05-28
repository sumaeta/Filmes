package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modal.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

	Page<Filme> findByTitulo(String titulo, PageRequest pageRequest);

}
