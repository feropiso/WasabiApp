package com.netbiis.restaurantes.pedido;

public class Pedido {
	
	private int idPedido;
	private int chav_estr_cliente;
	private int chav_estr_produto;
	private int quantidade;
	private String valorTotal;
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
	public int getChav_estr_cliente() {
		return chav_estr_cliente;
	}
	
	public void setChav_estr_cliente(int chav_estr_cliente) {
		this.chav_estr_cliente = chav_estr_cliente;
	}
	
	public int getChav_estr_produto() {
		return chav_estr_produto;
	}
	
	public void setChav_estr_produto(int chav_estr_produto) {
		this.chav_estr_produto = chav_estr_produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

}
