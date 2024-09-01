package br.com.lojavitual.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.StatusRastreio;
import br.com.lojavitual.repository.StatusRastreioRepository;

@Service
public class StatusRastreioService {

	@Autowired
	StatusRastreioRepository statusRastreioRepository;

	public List<StatusRastreio> buscaStatusRastreioTodas() {
		List<StatusRastreio>buscaStatusRastreioTodas=statusRastreioRepository.findAll();
		return buscaStatusRastreioTodas;
	}

	public StatusRastreio buscaStatusRastreioPorId(Long id) {
		StatusRastreio buscaStatusRastreioPorId=statusRastreioRepository.findById(id).get();
		return buscaStatusRastreioPorId;
	}

	public String deletarStatusRastreio(Long id) {
		String msg;
		if(!statusRastreioRepository.existsById(id)) {
			msg = "Não existe Status Rastreio com esse id ou ja foi deletado";
		}else {
			statusRastreioRepository.deleteById(id);
			msg="Deletado com sucesso";
		}
		
		return msg;
	}

	public StatusRastreio salvarStatusRastreio(@Valid StatusRastreio statusRastreio) {
		statusRastreio=statusRastreioRepository.save(statusRastreio);
		return statusRastreio;
	}
}
