package com.vendas.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.dto.AtualizacaoStatusPedidoDTO;
import com.vendas.dto.InformacaoItemPedidoDTO;
import com.vendas.dto.InformacoesPedidoDTO;
import com.vendas.dto.PedidoDTO;
import com.vendas.entity.ItemPedido;
import com.vendas.entity.Pedido;
import com.vendas.enums.StatusPedido;
import com.vendas.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody @Valid PedidoDTO dto) {
		Pedido pedido = pedidoService.salvar(dto);
		return pedido.getId();
	}

	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoService
					.obterPedidoCompleto(id)
					.map(p -> converter(p))
					.orElseThrow(() -> 
							new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
	}
	
	@PatchMapping("{id}")
	@ResponseStatus
	public void updateStatus(@PathVariable Integer id,
							@RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		InformacoesPedidoDTO dto = new InformacoesPedidoDTO();
		dto.setCodigo(pedido.getId());
		dto.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dto.setCpf(pedido.getCliente().getCpf());
		dto.setNomeCliente(pedido.getCliente().getNome());
		dto.setTotal(pedido.getTotal());
		dto.setItems(converter(pedido.getItens()));
		dto.setStatus(pedido.getStatus().name());
		return dto;
	}

	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(
					item -> new InformacaoItemPedidoDTO
						(item.getProduto().getDescricao(), 
						item.getProduto().getPreco().toString(), 
						item.getQuantidade())
				).collect(Collectors.toList());
	}
	
}
