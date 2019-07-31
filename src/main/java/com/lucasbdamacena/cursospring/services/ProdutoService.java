package com.lucasbdamacena.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasbdamacena.cursospring.domain.Categoria;
import com.lucasbdamacena.cursospring.domain.Produto;
import com.lucasbdamacena.cursospring.repositories.CategoriaRepository;
import com.lucasbdamacena.cursospring.repositories.ProdutoRepository;
import com.lucasbdamacena.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto find(Integer id) {
		Optional<Produto> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id + 
				"Tipo: " + Produto.class.getName()));
	}
	
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
