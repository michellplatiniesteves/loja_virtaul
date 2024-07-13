package br.com.lojavitual.model;

import java.io.Serializable;

public class ObjetoErroDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String erro;
	private String code;
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "ObjetoErroDTO [erro=" + erro + ", code=" + code + "]";
	}
	
	
}
