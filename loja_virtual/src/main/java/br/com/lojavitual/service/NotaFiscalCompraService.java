package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.NotaFiscalCompra;
import br.com.lojavitual.repository.NotaFiscalCompraRepository;

@Service
public class NotaFiscalCompraService {

	@Autowired
	private NotaFiscalCompraRepository notaFiscalCompraRepository;

	public List<NotaFiscalCompra> buscarTodasNotaFiscalCompra() {
		List<NotaFiscalCompra> listabuscarTodasNotaFiscalCompra = notaFiscalCompraRepository.findAll();
		return listabuscarTodasNotaFiscalCompra;
	}

	public List<NotaFiscalCompra> buscarNotaFiscalCompraDesc(String desc) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraDesc = notaFiscalCompraRepository.buscarNotaFiscalCompraDesc(desc);
		return listabuscarNotaFiscalCompraDesc;
	}

	public List<NotaFiscalCompra>  buscarNotaFiscalCompraPorEmpresa(Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorEmpresa = notaFiscalCompraRepository.buscarNotaFiscalCompraPorEmpresa(id);
		return listabuscarNotaFiscalCompraPorEmpresa;
	}

	public List<NotaFiscalCompra>  buscarNotaFiscalCompraPorContaPagar(Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorContaPagar = notaFiscalCompraRepository.buscarNotaFiscalCompraPorContaPagar(id);
		return listabuscarNotaFiscalCompraPorContaPagar;
	}
	public List<NotaFiscalCompra>  buscarNotaFiscalCompraPorPessoa(Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorPessoa = notaFiscalCompraRepository.buscarNotaFiscalCompraPorPessoa(id);
		return listabuscarNotaFiscalCompraPorPessoa;
	}
	
	public List<NotaFiscalCompra> buscarNotaFiscalCompraNumeroNota(String numeroNota) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraNumeroNota =notaFiscalCompraRepository.buscarNotaFiscalCompraNumeroNota(numeroNota);
		return listabuscarNotaFiscalCompraNumeroNota;
	}

	public List<NotaFiscalCompra> buscarNotaFiscalCompraPorId(Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorId =notaFiscalCompraRepository.buscarNotaFiscalCompraPorId(id);
		return listabuscarNotaFiscalCompraPorId;
	}
	public void deletarNotaFiscalCompraPorId(Long id) {
		notaFiscalCompraRepository.deletarItemNotaItemProduto(id);
		notaFiscalCompraRepository.deleteById(id);

	}

	public NotaFiscalCompra salvarNotaFiscalCompra(NotaFiscalCompra notaFiscalCompra) throws ExceptionMentoriaJava {
		if(notaFiscalCompra == null) {
			throw new ExceptionMentoriaJava("Informe uma nota fiscal de compra");
		}
		if(notaFiscalCompra.getId() == null && notaFiscalCompraRepository.existeNotaFiscalCompra(notaFiscalCompra.getDescricaoObs()) ) {
			throw new ExceptionMentoriaJava("Descrição já cadastrada para uma nota fiscal de compra");
		}
		if(notaFiscalCompra.getPessoa() == null && notaFiscalCompra.getPessoa().getId()<0 ) {
			throw new ExceptionMentoriaJava("Informe a Pessoa para  uma nota fiscal de compra");
		}
		if(notaFiscalCompra.getContaPagar() == null && notaFiscalCompra.getContaPagar().getId()<0 ) {
			throw new ExceptionMentoriaJava("Informe a Conta Pagar para  uma nota fiscal de compra");
		}
		if(notaFiscalCompra.getEmpresa() == null && notaFiscalCompra.getEmpresa().getId()<0 ) {
			throw new ExceptionMentoriaJava("Informe a Empresa para  uma nota fiscal de compra");
		}
		notaFiscalCompra = notaFiscalCompraRepository.save(notaFiscalCompra);
		return notaFiscalCompra;
	}


}
