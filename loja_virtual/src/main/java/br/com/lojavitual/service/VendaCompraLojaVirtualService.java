package br.com.lojavitual.service;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lojavirtual.apitransporte.enums.ApiTokenIntregracao;
import br.com.lojavitual.DTO.ConsultaFreteDTO;
import br.com.lojavitual.DTO.EmpresaTransporteDTO;
import br.com.lojavitual.DTO.EnvioEtiquetaDTO;
import br.com.lojavitual.DTO.ItemVendaDTO;
import br.com.lojavitual.DTO.RelatorioProdutosDTO;
import br.com.lojavitual.DTO.RelatorioProdutosEstoqueMinimoDTO;
import br.com.lojavitual.DTO.VendaCompraLojaVirtualDTO;
import br.com.lojavitual.controller.NotaFiscalVendaController;
import br.com.lojavitual.controller.PessoaFisicaController;
import br.com.lojavitual.controller.StatusRastreioController;
import br.com.lojavitual.enums.StatusContaReceber;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.ContaReceber;
import br.com.lojavitual.model.Endereco;
import br.com.lojavitual.model.ItemVendaLoja;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.model.StatusRastreio;
import br.com.lojavitual.model.VendaCompraLojaVirtual;
import br.com.lojavitual.repository.EnderecoRepository;
import br.com.lojavitual.repository.VendaCompraLojaVirtualRepository;
import br.com.lojavitual.util.VendaCompraLojaVirtualUtilitaria;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
	private VendaCompraLojaVirtualUtilitaria vendaCompraLojaVirtualUtilitaria;
	
	@Autowired
	private ContaReceberService contaReceberService;
	
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

		ContaReceber contaReceber = new ContaReceber();
		contaReceber.setDescricao("Venda loja virtual");
		contaReceber.setDtPagamento(vendaCompraLojaVirtual.getDataVenda());
		contaReceber.setDtVencimento(vendaCompraLojaVirtual.getDataVenda());
		contaReceber.setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		contaReceber.setPessoa(vendaCompraLojaVirtual.getPessoa());
		contaReceber.setStatusContaReceber(StatusContaReceber.QUITADA);
		contaReceber.setValorDesconto(vendaCompraLojaVirtual.getCupDesc().getValorRealDesc());
		contaReceber.setValorTotal(vendaCompraLojaVirtual.getValorTotal());
		
		contaReceberService.salvarContaPagar(contaReceber);
		
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
	public VendaCompraLojaVirtual  buscarVendaCompraLojaVirtualPorId(Long id) {

		VendaCompraLojaVirtual listabuscarVendaCompraLojaVirtualPorId =  vendaCompraLojaVirtualRepository.findById(id).get();
		return listabuscarVendaCompraLojaVirtualPorId;
		 
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

	public List<RelatorioProdutosDTO> relatorioProdutos(RelatorioProdutosDTO dto) {
		List<RelatorioProdutosDTO> resultado = vendaCompraLojaVirtualUtilitaria.relatorioProdutos(dto);
		return resultado;
	}
	public List<RelatorioProdutosEstoqueMinimoDTO> relatorioProdutosEstoqueMinimo(RelatorioProdutosEstoqueMinimoDTO dto) {
		List<RelatorioProdutosEstoqueMinimoDTO> resultado = vendaCompraLojaVirtualUtilitaria.relatorioProdutosEstoqueMinimo(dto);
		return resultado;
	}

	public List<EmpresaTransporteDTO> consultaFrete(ConsultaFreteDTO dto) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(dto);
		 OkHttpClient client = new OkHttpClient().newBuilder().build();

		 MediaType mediaType = MediaType.parse("application/json");
		 RequestBody body = RequestBody.create(mediaType,json);
		 Request request = new Request.Builder()
		   .url(ApiTokenIntregracao.URL_MELHOR_ENVIO+"/api/v2/me/shipment/calculate")
		   .post(body)
		   .addHeader("Accept", "application/json")
		   .addHeader("Content-Type", "application/json")
		   .addHeader("Authorization", "Bearer "+ApiTokenIntregracao.TOKEN_SANDBOX)
		   .addHeader("User-Agent", "michellplatini@gmail.com")
		   .build();

		 Response response = client.newCall(request).execute();
		 //System.out.println(response.body().string());
		 JsonNode jsonNode = new ObjectMapper().readTree(response.body().string());
		 Iterator<JsonNode> iterator =jsonNode.iterator();
		 List<EmpresaTransporteDTO>listaempresas = new ArrayList<EmpresaTransporteDTO>();
		 while(iterator.hasNext()) {
			 EmpresaTransporteDTO empresaTransporteDTO = new EmpresaTransporteDTO();
			JsonNode node = iterator.next() ;
			if(node.get("id")!= null) {
				empresaTransporteDTO.setId(node.get("id").asText());
			}
			if(node.get("name")!= null) {
				empresaTransporteDTO.setNome(node.get("name").asText());
			}
			if(node.get("price")!= null) {
				empresaTransporteDTO.setValor(node.get("price").asText());
			}
			if(node.get("company")!= null) {
				empresaTransporteDTO.setEmpresa(node.get("company").get("name").asText());
				empresaTransporteDTO.setPicture(node.get("company").get("picture").asText());
			}
	        if(empresaTransporteDTO.dadosOK()) {
	        	listaempresas.add(empresaTransporteDTO);
	        }
		 }
		return listaempresas;
	}

	public String ImprimeCompraEtiquetaFrete(Long idVenda) throws IOException, ExceptionMentoriaJava {
		String result = null;
		VendaCompraLojaVirtual compraLojaVirtual = vendaCompraLojaVirtualRepository
				.buscaLogicaVendaCompraLojaVirtualPorId(idVenda);
		if (compraLojaVirtual == null) {
			result = "Venda não encontrada";
			return result;
		}
		List<Endereco> enderecospj = enderecoRepository.buscaEnderecosEmpresa(compraLojaVirtual.getEmpresa().getId());
		compraLojaVirtual.getEmpresa().setEnderecos(enderecospj);
		EnvioEtiquetaDTO envioEtiquetaDTO = new EnvioEtiquetaDTO();
		envioEtiquetaDTO = envioEtiquetaDTO.converter(compraLojaVirtual);
		
		String jsonEnvio = new ObjectMapper().writeValueAsString(envioEtiquetaDTO);
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,jsonEnvio);
		Request request = new Request.Builder()
		  .url(ApiTokenIntregracao.URL_MELHOR_ENVIO+"/api/v2/me/cart")
		  .post(body)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer "+ApiTokenIntregracao.TOKEN_SANDBOX)
		  .addHeader("User-Agent", "michellplatini@gmail.com")
		  .build();

		Response response = client.newCall(request).execute();
		String respostaJson = response.body().string();
		
		if (respostaJson.contains("error")) {
			throw new ExceptionMentoriaJava(respostaJson);
		}
		
		JsonNode jsonNode = new ObjectMapper().readTree(respostaJson);
		
		
		Iterator<JsonNode> iterator = jsonNode.iterator();
		
		String idEtiqueta = "";
		
		while(iterator.hasNext()) {
			JsonNode node = iterator.next();
			 if (node.get("id") != null) {
			   idEtiqueta = node.get("id").asText();
			 }else {
				 idEtiqueta= node.asText(); 
			 }
			break;
		}
		
		vendaCompraLojaVirtualRepository.updateEtiqueta(idEtiqueta,compraLojaVirtual.getId());
		compraLojaVirtual.setCodigoEtiqueta(idEtiqueta);
		OkHttpClient client1 = new OkHttpClient().newBuilder().build();

		MediaType mediaType1 = MediaType.parse("application/json");
		RequestBody body1 = RequestBody.create(mediaType1, "{\"orders\":[\""+idEtiqueta+"\"]}");
		Request request1 = new Request.Builder()
				.url(ApiTokenIntregracao.URL_MELHOR_ENVIO + "/api/v2/me/shipment/checkout")
				.post(body1)
				.addHeader("Accept", "application/json").addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "Bearer " + ApiTokenIntregracao.TOKEN_SANDBOX)
				.addHeader("User-Agent", "michellplatini@gmail.com").build();

		Response response1 = client1.newCall(request1).execute();
		if(!response1.isSuccessful()) {
			 
			return result="Não foi possivel comprar a etiqueta";
		}
		OkHttpClient cliente = new OkHttpClient().newBuilder().build();

		MediaType mediaTypee = MediaType.parse("application/json");
		RequestBody bodye = RequestBody.create(mediaTypee, "{\"orders\":[\""+idEtiqueta+"\"]}");
		Request requeste = new Request.Builder()
		  .url(ApiTokenIntregracao.URL_MELHOR_ENVIO+"/api/v2/me/shipment/generate")
		  .post(bodye)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer "+ApiTokenIntregracao.TOKEN_SANDBOX)
		  .addHeader("User-Agent", "michellplatini@gmail.com")
		  .build();

		Response responsee = cliente.newCall(requeste).execute();
		if(!responsee.isSuccessful()) {
			 
			return result="Não foi possivel gerar a etiqueta";
		}
		OkHttpClient clienti = new OkHttpClient().newBuilder().build();
		RequestBody bodyi = RequestBody.create(mediaType, "{\"mode\":\"\",\"orders\":[\""+idEtiqueta+"\"]}");
		Request requesti = new Request.Builder()
		  .url(ApiTokenIntregracao.URL_MELHOR_ENVIO+"/api/v2/me/shipment/print")
		  .post(bodyi)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer "+ApiTokenIntregracao.TOKEN_SANDBOX)
		  .addHeader("User-Agent", "michellplatini@gmail.com")
		  .build();

		Response responsei = clienti.newCall(requesti).execute();
		if(!responsei.isSuccessful()) {
			 
			return result="Não foi possivel imprimir a etiqueta";
		}
		String urlEtiqueta =responsei.toString(); 
		vendaCompraLojaVirtualRepository.updateUrlEtiqueta(urlEtiqueta,compraLojaVirtual.getId());
		return result="salvo com sucesso";
	}	

}
