package br.com.lojavitual;

import br.com.lojavitual.util.ValidaCNPJ;

public class TesteValidacaoCpfCnpj {

	public static void main(String[] args) {
		Boolean isvalido= ValidaCNPJ.isCNPJ("72.337.653/0001-71");
		System.out.print(isvalido);

	}

}
