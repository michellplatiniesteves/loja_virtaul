package br.com.lojavitual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
@SequenceGenerator(sequenceName = "seq_produto", name = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator ="seq_produto",strategy = GenerationType.SEQUENCE )
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(columnDefinition = "text",length = 2500)
	private String descricao;
	
	@Column(nullable = false)
	private Double peso=0.0;
	@Column(nullable = false)
	private Double altura=0.0;
	@Column(nullable = false)
	private Double largura=0.0;
	@Column(nullable = false)
	private Double profundidade=0.0;
	@Column(nullable = false)
	private BigDecimal valorVenda=BigDecimal.ZERO;
	private Integer qtdEstoque=0;
	@Column(nullable = false)
	private Integer qtdeAlertaEstoque=0;
	private String linkYoutube;
	private Boolean alertaQtdeEstoque=Boolean.FALSE;
	private Boolean ativo =Boolean.TRUE;
	private Integer qtdClique =0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getLargura() {
		return largura;
	}
	public void setLargura(Double largura) {
		this.largura = largura;
	}
	public Double getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Integer getQtdeAlertaEstoque() {
		return qtdeAlertaEstoque;
	}
	public void setQtdeAlertaEstoque(Integer qtdeAlertaEstoque) {
		this.qtdeAlertaEstoque = qtdeAlertaEstoque;
	}
	public String getLinkYoutube() {
		return linkYoutube;
	}
	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}
	public Boolean getAlertaQtdeEstoque() {
		return alertaQtdeEstoque;
	}
	public void setAlertaQtdeEstoque(Boolean alertaQtdeEstoque) {
		this.alertaQtdeEstoque = alertaQtdeEstoque;
	}
	public Integer getQtdClique() {
		return qtdClique;
	}
	public void setQtdClique(Integer qtdClique) {
		this.qtdClique = qtdClique;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", peso=" + peso + ", altura="
				+ altura + ", largura=" + largura + ", profundidade=" + profundidade + ", valorVenda=" + valorVenda
				+ ", qtdEstoque=" + qtdEstoque + ", qtdeAlertaEstoque=" + qtdeAlertaEstoque + ", linkYoutube="
				+ linkYoutube + ", alertaQtdeEstoque=" + alertaQtdeEstoque + ", ativo=" + ativo + ", qtdClique="
				+ qtdClique + "]";
	}
	
	
	
	

}
