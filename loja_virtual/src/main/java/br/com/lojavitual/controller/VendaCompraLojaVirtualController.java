package br.com.lojavitual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
