package br.com.lojavitual.util;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.lojavitual.DTO.CepDTO;
import br.com.lojavitual.DTO.ConsultaCnpjDTO;

@Service
public class Utilitaria {

	public CepDTO consultaCep(String cep) {
		return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class).getBody();
	}
	public ConsultaCnpjDTO consultaCnjpReceita(String cnpj) {
		return new RestTemplate().getForEntity("https://receitaws.com.br/v1/cnpj/"+cnpj, ConsultaCnpjDTO.class).getBody();
	}
}
