package br.com.lojavitual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Size(min = 10,message = "O nome de produto deve conter minimo 10 letras")
	@NotNull(message = "Informe o nome do produto")
	@NotEmpty(message = "Informe o nome do produto")
	@Column(nullable = false)
	private String nome;
	
	@NotNull(message = "Informe a descrição do produto")
	@NotEmpty(message = "Informe a descrição do produto")
	@Column(columnDefinition = "text",length = 2500)
	private String descricao;
	
	@NotNull(message = "Informe o Tipo da Unidade")
	@NotEmpty(message = "Informe o Tipo da Unidade")
	@Column(nullable = false)
	private String tipoUnidade;
	
	@Min(value = 1,message = "Informe valores acima de 0")
	@NotNull(message = "Informe o peso")
	@Column(nullable = false)
	private Double peso;
	
	@NotNull(message = "Informe a altura")
	@Column(nullable = false)
	private Double altura;
	
	@NotNull(message = "Informe a largura")
	@Column(nullable = false)
	private Double largura;
	
	@NotNull(message = "Informe a profundidade")
	@Column(nullable = false)
	private Double profundidade;
	
	@NotNull(message = "Informe o Valor de Venda")
	@Column(nullable = false)
	private BigDecimal valorVenda=BigDecimal.ZERO;
	
	private Integer qtdEstoque=0;
	
	@Column(nullable = false)
	private Integer qtdeAlertaEstoque=0;
	
	private String linkYoutube;
	private Boolean alertaQtdeEstoque=Boolean.FALSE;
	private Boolean ativo =Boolean.TRUE;
	private Integer qtdClique =0;
	
	
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "empresa_fk"))
	private PessoaJuridica empresa;
	

	@ManyToOne(targetEntity=CategoriaProduto.class)
	@JoinColumn(name = "categoria_produto_id",nullable=false, foreignKey=@ForeignKey(value=ConstraintMode.CONSTRAINT,name= "categoria_produto_fk"))
	private CategoriaProduto categoriaProduto;
	

	@ManyToOne(targetEntity= MarcaProduto.class)
	@JoinColumn(name = "marca_produto_id",nullable=false, foreignKey=@ForeignKey(value=ConstraintMode.CONSTRAINT,name= "marca_produto_fk"))
	private MarcaProduto marcaProduto;
	

	@OneToMany(mappedBy = "produto",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<ImagemProduto>imagens = new ArrayList<ImagemProduto>();
	
	public void setMarcaProduto(MarcaProduto marcaProduto) {
		this.marcaProduto = marcaProduto;
	}
	
	public MarcaProduto getMarcaProduto() {
		return marcaProduto;
	}
	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}
	public String getTipoUnidade() {
		return tipoUnidade;
	}
	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}
	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}
	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	public PessoaJuridica getEmpresa() {
		return empresa;
	}
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
	
	public List<ImagemProduto> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemProduto> imagens) {
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", tipoUnidade=" + tipoUnidade
				+ ", peso=" + peso + ", altura=" + altura + ", largura=" + largura + ", profundidade=" + profundidade
				+ ", valorVenda=" + valorVenda + ", qtdEstoque=" + qtdEstoque + ", qtdeAlertaEstoque="
				+ qtdeAlertaEstoque + ", linkYoutube=" + linkYoutube + ", alertaQtdeEstoque=" + alertaQtdeEstoque
				+ ", ativo=" + ativo + ", qtdClique=" + qtdClique + ", empresa=" + empresa + ", categoriaProduto="
				+ categoriaProduto + ", marcaProduto=" + marcaProduto + ", imagens=" + imagens + "]";
	}
	
	
	
	

}
