package com.lucasbdamacena.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasbdamacena.cursospring.domain.Categoria;
import com.lucasbdamacena.cursospring.repositories.CategoriaRepository;
import com.lucasbdamacena.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = repo.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! Id:" + id + ", Tipo: "+ 
				Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
