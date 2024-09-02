package br.com.lojavitual.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.DTO.ItemVendaDTO;
import br.com.lojavitual.DTO.VendaCompraLojaVirtualDTO;
import br.com.lojavitual.controller.NotaFiscalVendaController;
import br.com.lojavitual.controller.PessoaFisicaController;
import br.com.lojavitual.controller.StatusRastreioController;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Endereco;
import br.com.lojavitual.model.ItemVendaLoja;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.model.StatusRastreio;
import br.com.lojavitual.model.VendaCompraLojaVirtual;
import br.com.lojavitual.repository.EnderecoRepository;
import br.com.lojavitual.repository.VendaCompraLojaVirtualRepository;
import br.com.lojavitual.util.VendaCompraLojaVirtualUtilitaria;

@Service
public class VendaCompraLojaVirtualService {

	@Autowired
	private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;
	
	@Autowired
	private StatusRastreioController statusRastreioController;
	
	@Autowired
	private NotaFiscalVendaController notaFiscalVendaController ;
	
	@Autowired
	VendaCompraLojaVirtualUtilitaria vendaCompraLojaVirtualUtilitaria;
	
	public VendaCompraLojaVirtualDTO salvarVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionMentoriaJava {
		
		vendaCompraLojaVirtual.getPessoa().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaCompraLojaVirtual.getPessoa()).getBody();
		
		vendaCompraLojaVirtual.getEnderecoCobranca().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoCobranca().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		
		Endereco enderecocob = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoCobranca());
		vendaCompraLojaVirtual.setEnderecoCobranca(enderecocob);
		vendaCompraLojaVirtual.setPessoa(pessoaFisica);

		vendaCompraLojaVirtual.getEnderecoEntrega().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoEntrega().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		
		Endereco enderecoent = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoEntrega());
		vendaCompraLojaVirtual.setEnderecoEntrega(enderecoent);
		vendaCompraLojaVirtual.getNotaFiscalVenda().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		
		for (int i = 0; i < vendaCompraLojaVirtual.getItemVendaLojas().size(); i++) {
			vendaCompraLojaVirtual.getItemVendaLojas().get(i).setEmpresa(vendaCompraLojaVirtual.getEmpresa());
			vendaCompraLojaVirtual.getItemVendaLojas().get(i).setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		}
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		
		vendaCompraLojaVirtual.getNotaFiscalVenda().setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		
		notaFiscalVendaController.salvarNotaFiscalVenda(vendaCompraLojaVirtual.getNotaFiscalVenda());
		
		StatusRastreio statusRastreio = new StatusRastreio();
		
		statusRastreio.setCidade("Local");
		statusRastreio.setCentroDistribuicao("Local");
		statusRastreio.setEstado("Local");
		statusRastreio.setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		statusRastreio.setStatus("Iniciando Venda");
		statusRastreio.setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		statusRastreioController.salvarStatusRastreio(statusRastreio);

		VendaCompraLojaVirtualDTO dto = new VendaCompraLojaVirtualDTO();
		
		for (ItemVendaLoja item : vendaCompraLojaVirtual.getItemVendaLojas()) {
			ItemVendaDTO itemDTO = new ItemVendaDTO();
			itemDTO.setProduto(item.getProduto());
			itemDTO.setQuantidade(item.getQuantidade());
			dto.getItemVendaLoja().add(itemDTO);
		}
		return dto.converter(vendaCompraLojaVirtual);
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
	public String deletarVendaCompraLojaVirtualEncadeado(Long id) {
		String msg;
		if (!vendaCompraLojaVirtualRepository.existsById(id)) {
			msg = "O id passado já foi deletado ou não existe";
		} else {
			vendaCompraLojaVirtualUtilitaria.deletarEncadeado(id);
			msg = "Deletdo com sucesso";
		}

		return msg;
	}
	public String deletarVendaCompraLojaVirtualLogica(Long id) {
		String msg;
		if (!vendaCompraLojaVirtualRepository.existsById(id)) {
			msg = "O id passado já foi deletado ou não existe";
		} else {
			vendaCompraLojaVirtualUtilitaria.exclusaoLogica(id);
			msg = "Deletdo com sucesso";
		}

		return msg;
	}
	public VendaCompraLojaVirtualDTO  buscarVendaCompraLojaVirtualPorId(Long id) {
		VendaCompraLojaVirtualDTO dto = new VendaCompraLojaVirtualDTO();
		VendaCompraLojaVirtual listabuscarVendaCompraLojaVirtualPorId =  vendaCompraLojaVirtualRepository.findById(id).get();
		return dto.converter(listabuscarVendaCompraLojaVirtualPorId);
		 
	}

	public VendaCompraLojaVirtualDTO  buscaLogicaVendaCompraLojaVirtualPorId(Long id) {
		VendaCompraLojaVirtualDTO dto = new VendaCompraLojaVirtualDTO();
		VendaCompraLojaVirtual listabuscarVendaCompraLojaVirtualPorId =  vendaCompraLojaVirtualRepository.buscaLogicaVendaCompraLojaVirtualPorId(id);
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

	public String ativacaoLogica(Long id ) {
		vendaCompraLojaVirtualUtilitaria.ativacaoLogica(id);
		var msg="Ativado com sucesso";
		return msg;
	}

	public List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualDinamica(String id, String tipo) {
		List<VendaCompraLojaVirtual> listaDinamica = new ArrayList<VendaCompraLojaVirtual>();	
		if(tipo.equalsIgnoreCase("Pessoa")) {
			listaDinamica = vendaCompraLojaVirtualRepository.buscarVendaCompraLojaVirtualPorPessoa(Long.valueOf(id));
		}else if(tipo.equalsIgnoreCase("Empresa")) {
			listaDinamica = vendaCompraLojaVirtualRepository.buscarVendaCompraLojaVirtualPorEmpresa(Long.valueOf(id));
		}else if(tipo.equalsIgnoreCase("NotaFiscalVenda")) {
			listaDinamica = vendaCompraLojaVirtualRepository.buscarVendaCompraLojaVirtualPorNotaFiscalVenda(Long.valueOf(id));
		}else if(tipo.equalsIgnoreCase("FormaPagament")) {
			listaDinamica = vendaCompraLojaVirtualRepository.buscarVendaCompraLojaVirtualPorFormaPagamento(Long.valueOf(id));
		}
		return listaDinamica;
	}

	public List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorIntervalo(String data1, String data2) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		Date data1Formatada = (Date) formato.parse(data1); 
		Date data2Formatada = (Date) formato.parse(data2); 
		List<VendaCompraLojaVirtual> listaPorIntervalo = vendaCompraLojaVirtualRepository.buscarVendaCompraLojaVirtualPorIntervalo(data1Formatada,data2Formatada);
		return listaPorIntervalo;
	}
}
