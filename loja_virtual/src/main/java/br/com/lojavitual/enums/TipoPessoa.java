package br.com.lojavitual.enums;

public enum TipoPessoa {
	PESSOAFISICA("PF"), PESSOAJURIDICA("PJ"), FORNECEDOR("F");

	private String descricao;

	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	@Override
	public String toString() {
		return this.descricao;
	}
}
