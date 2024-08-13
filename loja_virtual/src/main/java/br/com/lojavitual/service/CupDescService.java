package br.com.lojavitual.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.CupDesc;
import br.com.lojavitual.repository.CupDescRepository;

@Service
public class CupDescService {

	@Autowired
	CupDescRepository cupDescRepository;

	public List<CupDesc> buscaCupDescTodas() {
		List<CupDesc> buscaCupDescTodas= cupDescRepository.findAll();
		return buscaCupDescTodas;
	}

	public CupDesc buscaCupDescPorId(Long id) {
		CupDesc buscaCupDescPorId =cupDescRepository.findById(id).get();
		return buscaCupDescPorId;
	}

	public String deletarCupDesc(Long id) {
		String msg;
		if(!cupDescRepository.existsById(id)) {
			msg = "Não existe Cupom desconto com esse id ou ja foi deletado";
		}else {
			cupDescRepository.deleteById(id);
			msg="Deletado com sucesso";
		}
		
		return msg;
	}

	public CupDesc salvarCupDesc(CupDesc cupDesc) {
		cupDesc = cupDescRepository.save(cupDesc);
		return cupDesc;
	}

}
