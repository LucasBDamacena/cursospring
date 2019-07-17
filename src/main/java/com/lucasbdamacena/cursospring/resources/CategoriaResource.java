package com.lucasbdamacena.cursospring.resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasbdamacena.cursospring.domain.Categoria;
import com.lucasbdamacena.cursospring.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {	

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {		
		Categoria c = service.buscar(id);		
		return ResponseEntity.ok().body(c);
	}
}