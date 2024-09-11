package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.CupDesc;
import br.com.lojavitual.model.NotaFiscalVenda;
import br.com.lojavitual.repository.NotaFiscalVendaRepository;

@Service
public class NotaFiscalVendaService {

	@Autowired
	NotaFiscalVendaRepository notaFiscalVendaRepository;

	public List<NotaFiscalVenda> buscaNotaFiscalVendaTodas() {
		List<NotaFiscalVenda> buscaNotaFiscalVendaTodas = notaFiscalVendaRepository.findAll();
		return buscaNotaFiscalVendaTodas;
	}

	public List<NotaFiscalVenda> buscaNotaFiscalVendaPorEmpresa(Long id) {
		List<NotaFiscalVenda> buscaNotaFiscalVendaPorEmpresa = notaFiscalVendaRepository.buscaNotaFiscalVendaPorEmpresa(id);
		return buscaNotaFiscalVendaPorEmpresa;
	}
	public List<NotaFiscalVenda> buscaNotaFiscalVendaPorVenda(Long id) {
		List<NotaFiscalVenda> buscaNotaFiscalVendaPorEmpresa = notaFiscalVendaRepository.buscaNotaFiscalVendaPorVenda(id);
		return buscaNotaFiscalVendaPorEmpresa;
	}
	public NotaFiscalVenda buscaNotaFiscalVendaPorId(Long id) {
		NotaFiscalVenda buscaNotaFiscalVendaPorId = notaFiscalVendaRepository.findById(id).get();
		return buscaNotaFiscalVendaPorId;
	}

	public String deletarNotaFiscalVenda(Long id) {
		String msg;
		if(!notaFiscalVendaRepository.existsById(id)) {
			msg = "Não existe Cupom desconto com esse id ou ja foi deletado";
		}else {
			notaFiscalVendaRepository.deleteById(id);
			msg="Deletado com sucesso";
		}
		
		return msg;
	}

	public NotaFiscalVenda salvarNotaFiscalVenda(NotaFiscalVenda notaFiscalVenda) {
		notaFiscalVenda = notaFiscalVendaRepository.save(notaFiscalVenda);
		return notaFiscalVenda;
	}

}
