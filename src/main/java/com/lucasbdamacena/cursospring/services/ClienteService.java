package com.lucasbdamacena.cursospring.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasbdamacena.cursospring.domain.Cliente;
import com.lucasbdamacena.cursospring.repositories.ClienteRepository;
import com.lucasbdamacena.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id + 
				"Tipo: " + Cliente.class.getName()));
	}
}
