package br.com.lojavitual.enums;

public enum StatusContaReceber {
	VENCIDA("Vencida"),
	QUITADA("Quitada"),
	ABERTA("Aberta"),
	COBRANCA("Pagar");
	 
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
   
	private StatusContaReceber(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {

		return this.descricao;
	}
}
