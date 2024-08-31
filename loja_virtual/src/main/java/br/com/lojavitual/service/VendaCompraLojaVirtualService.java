package br.com.lojavitual.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.DTO.VendaCompraLojaVirtualDTO;
import br.com.lojavitual.controller.NotaFiscalVendaController;
import br.com.lojavitual.controller.PessoaFisicaController;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Endereco;
import br.com.lojavitual.model.NotaFiscalVenda;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.model.VendaCompraLojaVirtual;
import br.com.lojavitual.repository.EnderecoRepository;
import br.com.lojavitual.repository.VendaCompraLojaVirtualRepository;

@Service
public class VendaCompraLojaVirtualService {

	@Autowired
	private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;
	@Autowired
	private NotaFiscalVendaController notaFiscalVendaController ;
	
	public VendaCompraLojaVirtualDTO salvarVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionMentoriaJava {
		
		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaCompraLojaVirtual.getPessoa()).getBody();
		Endereco enderecocob = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoCobranca());
		vendaCompraLojaVirtual.setEnderecoCobranca(enderecocob);
		vendaCompraLojaVirtual.getPessoa().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		vendaCompraLojaVirtual.setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoCobranca().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoCobranca().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		
		Endereco enderecoent = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoEntrega());
		vendaCompraLojaVirtual.setEnderecoEntrega(enderecoent);
		vendaCompraLojaVirtual.getEnderecoEntrega().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoEntrega().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);

		vendaCompraLojaVirtual.getNotaFiscalVenda().setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		notaFiscalVendaController.salvarNotaFiscalVenda(vendaCompraLojaVirtual.getNotaFiscalVenda());
		VendaCompraLojaVirtualDTO dto = new VendaCompraLojaVirtualDTO();
		dto.converter(vendaCompraLojaVirtual);
		return dto;
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
	public VendaCompraLojaVirtualDTO  buscarVendaCompraLojaVirtualPorId(Long id) {
		VendaCompraLojaVirtualDTO dto = new VendaCompraLojaVirtualDTO();
		VendaCompraLojaVirtual listabuscarVendaCompraLojaVirtualPorId =  vendaCompraLojaVirtualRepository.findById(id).get();
		return dto.converter(listabuscarVendaCompraLojaVirtualPorId);
		 
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
