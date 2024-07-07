package br.com.lojavitual.enums;

public enum StatusContaPagar {
	VENCIDA("Vencida"),
	QUITADA("Quitada"),
	ABERTA("Aberta"),
	COBRANCA("Pagar"),
	RENEGOCIADA("Renegociada");
	 
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
   
	private StatusContaPagar(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {

		return this.descricao;
	}
}
