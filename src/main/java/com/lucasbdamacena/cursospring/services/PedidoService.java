package com.lucasbdamacena.cursospring.services;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasbdamacena.cursospring.domain.ItemPedido;
import com.lucasbdamacena.cursospring.domain.PagamentoComBoleto;
import com.lucasbdamacena.cursospring.domain.Pedido;
import com.lucasbdamacena.cursospring.domain.enums.EstadoPagamento;
import com.lucasbdamacena.cursospring.repositories.ItemPedidoRepository;
import com.lucasbdamacena.cursospring.repositories.PagamentoRepository;
import com.lucasbdamacena.cursospring.repositories.PedidoRepository;
import com.lucasbdamacena.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "+ id + 
				"Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;		
	}
}
