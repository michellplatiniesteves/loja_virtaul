package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private String tipoUnidade;
	private Double peso=0.0;
	private Double altura=0.0;
	private Double largura=0.0;
	private Double profundidade=0.0;
	private BigDecimal valorVenda=BigDecimal.ZERO;
	private Integer qtdEstoque=0;
	private Integer qtdeAlertaEstoque=0;
	private String linkYoutube;
	private Boolean alertaQtdeEstoque=Boolean.FALSE;
	private Boolean ativo =Boolean.TRUE;
	private Integer qtdClique =0;
	private PessoaJuridicaDTO empresa;
	private CategoriaProdutoDTO categoriaProduto;
	private MarcaProdutoDTO marcaProduto;
	private List<ImagemProdutoDTO>imagens = new ArrayList<ImagemProdutoDTO>();
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
	public String getTipoUnidade() {
		return tipoUnidade;
	}
	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
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
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Integer getQtdClique() {
		return qtdClique;
	}
	public void setQtdClique(Integer qtdClique) {
		this.qtdClique = qtdClique;
	}
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
	}
	public CategoriaProdutoDTO getCategoriaProduto() {
		return categoriaProduto;
	}
	public void setCategoriaProduto(CategoriaProdutoDTO categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}
	public MarcaProdutoDTO getMarcaProduto() {
		return marcaProduto;
	}
	public void setMarcaProduto(MarcaProdutoDTO marcaProduto) {
		this.marcaProduto = marcaProduto;
	}
	public List<ImagemProdutoDTO> getImagens() {
		return imagens;
	}
	public void setImagens(List<ImagemProdutoDTO> imagens) {
		this.imagens = imagens;
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
		ProdutoDTO other = (ProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "ProdutoDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", tipoUnidade=" + tipoUnidade
				+ ", peso=" + peso + ", altura=" + altura + ", largura=" + largura + ", profundidade=" + profundidade
				+ ", valorVenda=" + valorVenda + ", qtdEstoque=" + qtdEstoque + ", qtdeAlertaEstoque="
				+ qtdeAlertaEstoque + ", linkYoutube=" + linkYoutube + ", alertaQtdeEstoque=" + alertaQtdeEstoque
				+ ", ativo=" + ativo + ", qtdClique=" + qtdClique + ", empresa=" + empresa + ", categoriaProduto="
				+ categoriaProduto + ", marcaProduto=" + marcaProduto + ", imagens=" + imagens + "]";
	}
	
	
}
