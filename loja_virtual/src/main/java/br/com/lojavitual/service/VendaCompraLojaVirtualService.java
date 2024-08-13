package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.NotaFiscalVenda;
import br.com.lojavitual.model.VendaCompraLojaVirtual;
import br.com.lojavitual.repository.VendaCompraLojaVirtualRepository;

@Service
public class VendaCompraLojaVirtualService {

	@Autowired
	VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;

	public VendaCompraLojaVirtual salvarVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		return vendaCompraLojaVirtual;
	}

	public String deletarVendaCompraLojaVirtual(Long id) {
		String msg;
		if (!vendaCompraLojaVirtualRepository.existsById(id)) {
			msg = "O id passado já foi deletado ou não existe";
		} else {
			vendaCompraLojaVirtualRepository.deleteById(id);
			msg = "Deletdo com sucesso";
		}

		return msg;
	}

	public List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorId(Long id) {
		List<VendaCompraLojaVirtual> listabuscarVendaCompraLojaVirtualPorId = (List<VendaCompraLojaVirtual>) vendaCompraLojaVirtualRepository
				.findById(id).get();
		return listabuscarVendaCompraLojaVirtualPorId;
	}

	public List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorPessoa(Long id) {
		List<VendaCompraLojaVirtual> listabuscarVendaCompraLojaVirtualPorPessoa = vendaCompraLojaVirtualRepository
				.buscarVendaCompraLojaVirtualPorPessoa(id);
		return listabuscarVendaCompraLojaVirtualPorPessoa;
	}

	public List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorEmpresa(Long id) {
		List<VendaCompraLojaVirtual> listabuscarVendaCompraLojaVirtualPorEmpresa = vendaCompraLojaVirtualRepository
				.buscarVendaCompraLojaVirtualPorEmpresa(id);
		return listabuscarVendaCompraLojaVirtualPorEmpresa;
	}

	public List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorNotaFiscalVenda(Long id) {
		List<VendaCompraLojaVirtual> listabuscarVendaCompraLojaVirtualPorNotaFiscalVenda = vendaCompraLojaVirtualRepository
				.buscarVendaCompraLojaVirtualPorNotaFiscalVenda(id);
		return listabuscarVendaCompraLojaVirtualPorNotaFiscalVenda;
	}

	public List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorFormaPagamento(Long id) {
		List<VendaCompraLojaVirtual> listabuscarVendaCompraLojaVirtualPorFormaPagamento = vendaCompraLojaVirtualRepository
				.buscarVendaCompraLojaVirtualPorFormaPagamento(id);
		return listabuscarVendaCompraLojaVirtualPorFormaPagamento;
	}

}
