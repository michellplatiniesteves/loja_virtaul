package br.com.lojavitual.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class RelatorioProdutosEstoqueMinimoDTO implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String id="";
 private String nome="";
 private String valorVenda="";
 @NotBlank(message = "Informa a data")
 private String dataInicial="";
 @NotBlank(message = "Informa a data")
 private String dataFinal="";
 private String quantidadeComprada="";
 private String idFornecedor="";
 private String nomeFornecedor="";
 private String quantidadeEstoqueAlerta="";

 public void setQuantidadeEstoqueAlerta(String quantidadeEstoqueAlerta) {
	this.quantidadeEstoqueAlerta = quantidadeEstoqueAlerta;
}
 public String getQuantidadeEstoqueAlerta() {
	return quantidadeEstoqueAlerta;
}
 private String dataCompra="";
 private String quantidadeEstoque="";
 
 
public String getDataInicial() {
	return dataInicial;
}
public void setDataInicial(String dataInicial) {
	this.dataInicial = dataInicial;
}
public String getDataFinal() {
	return dataFinal;
}
public void setDataFinal(String dataFinal) {
	this.dataFinal = dataFinal;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getValorVenda() {
	return valorVenda;
}
public void setValorVenda(String valorVenda) {
	this.valorVenda = valorVenda;
}
public String getQuantidadeComprada() {
	return quantidadeComprada;
}
public void setQuantidadeComprada(String quantidadeComprada) {
	this.quantidadeComprada = quantidadeComprada;
}
public String getIdFornecedor() {
	return idFornecedor;
}
public void setIdFornecedor(String idFornecedor) {
	this.idFornecedor = idFornecedor;
}
public String getNomeFornecedor() {
	return nomeFornecedor;
}
public void setNomeFornecedor(String nomeFornecedor) {
	this.nomeFornecedor = nomeFornecedor;
}
public String getDataCompra() {
	return dataCompra;
}
public void setDataCompra(String dataCompra) {
	this.dataCompra = dataCompra;
}
public String getQuantidadeEstoque() {
	return quantidadeEstoque;
}
public void setQuantidadeEstoque(String quantidadeEstoque) {
	this.quantidadeEstoque = quantidadeEstoque;
}
 

}
