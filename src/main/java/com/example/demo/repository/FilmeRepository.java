package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modal.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

}
