package br.com.lojavitual.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.DTO.ConsultaFreteDTO;
import br.com.lojavitual.DTO.EmpresaTransporteDTO;
import br.com.lojavitual.DTO.RelatorioProdutosDTO;
import br.com.lojavitual.DTO.RelatorioProdutosEstoqueMinimoDTO;
import br.com.lojavitual.DTO.VendaCompraLojaVirtualDTO;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.VendaCompraLojaVirtual;
import br.com.lojavitual.service.VendaCompraLojaVirtualService;
@RequestMapping("VendaCompraLojaVirtual/")
@RestController
public class VendaCompraLojaVirtualController {
	
	@Autowired
	VendaCompraLojaVirtualService vendaCompraLojaVirtualService;

	@ResponseBody
	@PostMapping(value = "salvarVendaCompraLojaVirtual")
	public ResponseEntity<VendaCompraLojaVirtualDTO>salvarVendaCompraLojaVirtual(@RequestBody @Valid VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionMentoriaJava{
		VendaCompraLojaVirtualDTO dto = vendaCompraLojaVirtualService.salvarVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		return new ResponseEntity<VendaCompraLojaVirtualDTO>(dto,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "deletarVendaCompraLojaVirtual/{id}")
	public ResponseEntity<String>deletarVendaCompraLojaVirtual(@PathVariable("id")Long id){
         String msg = vendaCompraLojaVirtualService.deletarVendaCompraLojaVirtual(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@ResponseBody
	@PutMapping(value = "ativacaoLogica/{id}")
	public ResponseEntity<String>ativacaoLogica(@PathVariable("id")Long id){
         String msg = vendaCompraLojaVirtualService.ativacaoLogica(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "deletarVendaCompraLojaVirtualEncadeado/{id}")
	public ResponseEntity<String>deletarVendaCompraLojaVirtualEncadeado(@PathVariable("id")Long id){
         String msg = vendaCompraLojaVirtualService.deletarVendaCompraLojaVirtualEncadeado(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "deletarVendaCompraLojaVirtualLogica/{id}")
	public ResponseEntity<String>deletarVendaCompraLojaVirtualLogica(@PathVariable("id")Long id){
         String msg = vendaCompraLojaVirtualService.deletarVendaCompraLojaVirtualLogica(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscaLogicaVendaCompraLojaVirtualPorId/{id}")
	public ResponseEntity<VendaCompraLojaVirtualDTO>buscaLogicaVendaCompraLojaVirtualPorId(@PathVariable("id")Long id){
		VendaCompraLojaVirtualDTO listabuscaLogicaVendaCompraLojaVirtualPorId = vendaCompraLojaVirtualService.buscaLogicaVendaCompraLojaVirtualPorId(id);
		return new ResponseEntity<VendaCompraLojaVirtualDTO>(listabuscaLogicaVendaCompraLojaVirtualPorId,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscarVendaCompraLojaVirtualPorId/{id}")
	public ResponseEntity<VendaCompraLojaVirtualDTO>buscarVendaCompraLojaVirtualPorId(@PathVariable("id")Long id){
		VendaCompraLojaVirtualDTO listabuscarVendaCompraLojaVirtualPorId = vendaCompraLojaVirtualService.buscarVendaCompraLojaVirtualPorId(id);
		return new ResponseEntity<VendaCompraLojaVirtualDTO>(listabuscarVendaCompraLojaVirtualPorId,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscarVendaCompraLojaVirtualPorPessoa/{id}")
	public ResponseEntity<List<VendaCompraLojaVirtual>>buscarVendaCompraLojaVirtualPorPessoa(@PathVariable("id")Long id){
		List<VendaCompraLojaVirtual>listabuscarVendaCompraLojaVirtualPorPessoa = vendaCompraLojaVirtualService.buscarVendaCompraLojaVirtualPorPessoa(id);
		return new ResponseEntity<List<VendaCompraLojaVirtual>>(listabuscarVendaCompraLojaVirtualPorPessoa,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscarVendaCompraLojaVirtualDinamica/{id}/{tipo}")
	public ResponseEntity<List<VendaCompraLojaVirtual>>buscarVendaCompraLojaVirtualDinamica(@PathVariable("id")String id,@PathVariable("tipo")String tipo){
		List<VendaCompraLojaVirtual>listabuscarVendaCompraLojaVirtualPorPessoa = vendaCompraLojaVirtualService.buscarVendaCompraLojaVirtualDinamica(id,tipo);
		return new ResponseEntity<List<VendaCompraLojaVirtual>>(listabuscarVendaCompraLojaVirtualPorPessoa,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscarVendaCompraLojaVirtualPorIntervalo/{data1}/{data2}")
	public ResponseEntity<List<VendaCompraLojaVirtual>>buscarVendaCompraLojaVirtualPorIntervalo(@PathVariable("data1")String data1,@PathVariable("data2")String data2) throws ParseException{
		List<VendaCompraLojaVirtual>listabuscarVendaCompraLojaVirtualPorPessoa = vendaCompraLojaVirtualService.buscarVendaCompraLojaVirtualPorIntervalo(data1,data2);
		return new ResponseEntity<List<VendaCompraLojaVirtual>>(listabuscarVendaCompraLojaVirtualPorPessoa,HttpStatus.OK);
	}
	@ResponseBody
	@PostMapping(value = "relatorioProdutosEstoqueMinimo")
	public ResponseEntity<List<RelatorioProdutosEstoqueMinimoDTO>>relatorioProdutosEstoqueMinimo( @RequestBody RelatorioProdutosEstoqueMinimoDTO dto) throws ParseException{
		List<RelatorioProdutosEstoqueMinimoDTO>relatorioProdutosEstoqueMinimo = vendaCompraLojaVirtualService.relatorioProdutosEstoqueMinimo(dto);
		return new ResponseEntity<List<RelatorioProdutosEstoqueMinimoDTO>>(relatorioProdutosEstoqueMinimo,HttpStatus.OK);
	}
	@ResponseBody
	@PostMapping(value = "relatorioProdutos")
	public ResponseEntity<List<RelatorioProdutosDTO>>relatorioProdutos( @RequestBody RelatorioProdutosDTO dto) throws ParseException{
		List<RelatorioProdutosDTO>RelatorioProdutos = vendaCompraLojaVirtualService.relatorioProdutos(dto);
		return new ResponseEntity<List<RelatorioProdutosDTO>>(RelatorioProdutos,HttpStatus.OK);
	}
	@ResponseBody
	@PostMapping(value = "consultaFrete")
	public ResponseEntity<List<EmpresaTransporteDTO>>consultaFrete( @RequestBody @Valid ConsultaFreteDTO dto) throws ParseException, IOException{
		List<EmpresaTransporteDTO>consultaFrete = vendaCompraLojaVirtualService.consultaFrete(dto);
		return new ResponseEntity<List<EmpresaTransporteDTO>>(consultaFrete,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscarVendaCompraLojaVirtualPorEmpresa/{id}")
	public ResponseEntity<List<VendaCompraLojaVirtual>>buscarVendaCompraLojaVirtualPorEmpresa(@PathVariable("id")Long id){
		List<VendaCompraLojaVirtual>listabuscarVendaCompraLojaVirtualPorEmpresa = vendaCompraLojaVirtualService.buscarVendaCompraLojaVirtualPorEmpresa(id);
		return new ResponseEntity<List<VendaCompraLojaVirtual>>(listabuscarVendaCompraLojaVirtualPorEmpresa,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscarVendaCompraLojaVirtualPorNotaFiscalVenda/{id}")
	public ResponseEntity<List<VendaCompraLojaVirtual>>buscarVendaCompraLojaVirtualPorNotaFiscalVenda(@PathVariable("id")Long id){
		List<VendaCompraLojaVirtual>listabuscarVendaCompraLojaVirtualPorNotaFiscalVenda = vendaCompraLojaVirtualService.buscarVendaCompraLojaVirtualPorNotaFiscalVenda(id);
		return new ResponseEntity<List<VendaCompraLojaVirtual>>(listabuscarVendaCompraLojaVirtualPorNotaFiscalVenda,HttpStatus.OK);
	}
	@GetMapping(value = "buscarVendaCompraLojaVirtualPorFormaPagamento/{id}")
	public ResponseEntity<List<VendaCompraLojaVirtual>>buscarVendaCompraLojaVirtualPorFormaPagamento(@PathVariable("id")Long id){
		List<VendaCompraLojaVirtual>listabuscarVendaCompraLojaVirtualPorFormaPagamento = vendaCompraLojaVirtualService.buscarVendaCompraLojaVirtualPorFormaPagamento(id);
		return new ResponseEntity<List<VendaCompraLojaVirtual>>(listabuscarVendaCompraLojaVirtualPorFormaPagamento,HttpStatus.OK);
	}
}
