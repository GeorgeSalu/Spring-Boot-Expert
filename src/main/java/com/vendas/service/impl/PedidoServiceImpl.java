package com.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.dto.ItemPedidoDTO;
import com.vendas.dto.PedidoDTO;
import com.vendas.entity.Cliente;
import com.vendas.entity.ItemPedido;
import com.vendas.entity.Pedido;
import com.vendas.entity.Produto;
import com.vendas.exception.RegraNegocioException;
import com.vendas.repository.ClientesRepository;
import com.vendas.repository.ItemsPedidoRepository;
import com.vendas.repository.PedidosRepository;
import com.vendas.repository.ProdutosRepository;
import com.vendas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private PedidosRepository pedidosRepository;
	private ClientesRepository clientesRepository;
	private ProdutosRepository produtosRepository;
	private ItemsPedidoRepository itemsPedidoRepository;

	public PedidoServiceImpl(PedidosRepository pedidosRepository
			, ClientesRepository clientesRepository, ProdutosRepository produtosRepository
			,ItemsPedidoRepository itemsPedidoRepository) {
		this.pedidosRepository = pedidosRepository;
		this.clientesRepository = clientesRepository;
		this.produtosRepository = produtosRepository;
		this.itemsPedidoRepository = itemsPedidoRepository;
	}

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository
								.findById(idCliente)
								.orElseThrow(() -> new RegraNegocioException("codigo de cliente invalido"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itemsPedido = converterItens(pedido, dto.getItems());
		pedidosRepository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new RegraNegocioException("nao é possivel realizar um pedido sem itenss");
		}
		
		return items
					.stream()
					.map(dto -> {
						Integer idProduto = dto.getProduto();

						Produto produto = produtosRepository.findById(idProduto)
							.orElseThrow(() -> new RegraNegocioException("Codigo de produto invalido"));
						
						
						ItemPedido itemPedido = new ItemPedido();
						itemPedido.setQuantidade(dto.getQuantidade());
						itemPedido.setPedido(pedido);
						itemPedido.setProduto(produto);
						return itemPedido;
					}).collect(Collectors.toList());
	}
}
