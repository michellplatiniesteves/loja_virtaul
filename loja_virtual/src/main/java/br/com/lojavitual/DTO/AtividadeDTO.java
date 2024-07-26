package br.com.lojavitual.DTO;

import java.io.Serializable;

public class AtividadeDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String code;
	String text;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "AtividadeDTO [code=" + code + ", text=" + text + "]";
	}
	
	
}
