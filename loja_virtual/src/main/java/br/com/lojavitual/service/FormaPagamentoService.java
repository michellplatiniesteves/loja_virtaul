package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.FormaPagamento;
import br.com.lojavitual.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	FormaPagamentoRepository formaPagamentoRepository;

	public List<FormaPagamento> buscaFormaPagamentoTodas() {
		List<FormaPagamento> buscaFormaPagamentoTodas = formaPagamentoRepository.findAll();
		return buscaFormaPagamentoTodas;
	}

	public FormaPagamento buscaFormaPagamentoPorId(Long id) {
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).get();
		return formaPagamento;
	}

	public String deletarFormaPagamento(Long id) {
		String msg;
		if (!formaPagamentoRepository.existsById(id)) {
			msg = "Id já foi deletado ou não existe";
		} else {
			formaPagamentoRepository.deleteById(id);
			msg = "Deletado com sucesso";
		}

		return msg;
	}

	public  FormaPagamento salvarFormaPagamento( FormaPagamento formaPagamento) {
		formaPagamento = formaPagamentoRepository.save(formaPagamento);
		return formaPagamento;
	}

}
