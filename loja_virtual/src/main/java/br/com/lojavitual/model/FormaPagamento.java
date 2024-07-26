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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "forma_pagamento")
@SequenceGenerator(sequenceName = "seq_forma_pagamento", name = "seq_forma_pagamento", allocationSize = 1, initialValue = 1)
public class FormaPagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_forma_pagamento")
	private Long id;

	@NotNull(message = "Informe a descrição")
	@NotEmpty(message = "Informe a descrição")
	@Column(nullable = false)
	private String descricao;

	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "empresa_fk"))
	private Pessoa empresa;
	
	@Override
	public String toString() {
		return "FormaPagamento [id=" + id + ", descricao=" + descricao + ", empresa=" + empresa + "]";
	}

	public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
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
		FormaPagamento other = (FormaPagamento) obj;
		return Objects.equals(id, other.id);
	}

}
