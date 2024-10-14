package br.com.lojavitual.DTO;

import java.io.Serializable;

public class InvoiceEnvioEtiquetaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		return key;
	}

}
