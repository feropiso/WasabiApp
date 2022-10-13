package com.netbiis.restaurantes.produto;

public class Produto {
	
	private int idProduto;
	private String preco;
	private String imagem;
	private String descricao;
	private String nomePrato;
	private String categoria; //Promoção, Entradas, Temaki Especial, Holl Wassabi, Bebida.
	
	
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNomePrato() {
		return nomePrato;
	}
	
	public void setNomePrato(String nomePrato) {
		this.nomePrato = nomePrato;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
		

}
