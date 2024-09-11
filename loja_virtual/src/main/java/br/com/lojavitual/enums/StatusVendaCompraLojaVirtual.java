package br.com.lojavitual.enums;

public enum StatusVendaCompraLojaVirtual {
	
	FINALIZADO("Finalizado"),
	ABERTO("Aberto"),
	CANCELADO("Cancelado"),
	ABANDONADO("Abandonado");
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
   
	private StatusVendaCompraLojaVirtual(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {

		return this.descricao;
	}
}
