package br.com.lojavitual.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "status_rastreio")
@SequenceGenerator(allocationSize = 1,initialValue = 1,name = "seq_status_rastreio",sequenceName ="seq_status_rastreio" )
public class StatusRastreio implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator ="seq_status_rastreio",strategy = GenerationType.SEQUENCE )
	private Long id;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = false)
	private String estado;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private String centroDistribuicao;
	@ManyToOne(targetEntity =VendaCompraLojaVirtual.class )
	@JoinColumn(name = "venda_compra_loja_virt_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "venda_compra_loja_virt_fk"))
	private VendaCompraLojaVirtual vendaCompraLojaVirtual;
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "empresa_fk"))
	private Pessoa empresa;
	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}
	public Pessoa getEmpresa() {
		return empresa;
	}
	public void setVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
		this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
	}
	public VendaCompraLojaVirtual getVendaCompraLojaVirtual() {
		return vendaCompraLojaVirtual;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCentroDistribuicao() {
		return centroDistribuicao;
	}
	public void setCentroDistribuicao(String centroDistribuicao) {
		this.centroDistribuicao = centroDistribuicao;
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
		StatusRastreio other = (StatusRastreio) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "StatusRastreio [id=" + id + ", cidade=" + cidade + ", estado=" + estado + ", status=" + status
				+ ", centroDistribuicao=" + centroDistribuicao + ", vendaCompraLojaVirtual=" + vendaCompraLojaVirtual
				+ ", empresa=" + empresa + "]";
	}

	
	
}
