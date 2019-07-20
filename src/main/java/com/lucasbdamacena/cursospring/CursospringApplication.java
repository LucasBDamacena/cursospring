package com.lucasbdamacena.cursospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasbdamacena.cursospring.domain.Categoria;
import com.lucasbdamacena.cursospring.domain.Cidade;
import com.lucasbdamacena.cursospring.domain.Cliente;
import com.lucasbdamacena.cursospring.domain.Endereco;
import com.lucasbdamacena.cursospring.domain.Estado;
import com.lucasbdamacena.cursospring.domain.Produto;
import com.lucasbdamacena.cursospring.domain.enums.TipoCliente;
import com.lucasbdamacena.cursospring.repositories.CategoriaRepository;
import com.lucasbdamacena.cursospring.repositories.CidadeRepository;
import com.lucasbdamacena.cursospring.repositories.ClienteRepository;
import com.lucasbdamacena.cursospring.repositories.EnderecoRepository;
import com.lucasbdamacena.cursospring.repositories.EstadoRepository;
import com.lucasbdamacena.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;	
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000);
		Produto p2 = new Produto(null, "Impressora", 800);	
		Produto p3 = new Produto(null, "Mouse", 80);
		
		c1.setProdutos(Arrays.asList(p1,p2,p3));
		c2.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(c1));
		p2.setCategorias(Arrays.asList(c1,c2));
		p3.setCategorias(Arrays.asList(c1));
					
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado e1 = new Estado(null, "São Paulo");
		Estado e2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null, "Uberlância", e2);
		Cidade cid2 = new Cidade(null, "São Paulo", e1);
		Cidade cid3 = new Cidade(null, "Campinas", e1);
		
		
		e1.setCidades(Arrays.asList(cid1));
		e2.setCidades(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));	
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "1123", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("289999999","2865965656"));
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Ap 303", "Jardim", "222222", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "10", "Ap 10", "Centro", "545", cli1, cid2);
		
		cli1.setEnderecos(Arrays.asList(end1, end2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		
		
	}

}
