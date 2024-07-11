package br.com.lojavitual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.model.Acesso;
import br.com.lojavitual.service.AcessoService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RestController
public class AcessoController {
    
	@Autowired
	AcessoService acessoService;
	
	@ResponseBody
	@PostMapping(value = "**/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
		
		Acesso acessoSalvo = acessoService.salvar(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "**/deletarAcesso")
	public ResponseEntity<?> deletarAcesso(@RequestBody Acesso acesso) {
		
		 acessoService.deletar(acesso.getId());
		return new ResponseEntity<>("deletado com sucesso",HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "**/deletarAcessoPorId/{id}")
	public ResponseEntity<?> deletarAcessoPorId(@PathVariable Long id) {
		
		 acessoService.deletarPorId(id);
		return new ResponseEntity<>("deletado com sucesso",HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "**/buscarAcesso")
	public ResponseEntity<List<Acesso>> buscarAcesso(@RequestBody Acesso acesso) {
		
		 List<Acesso> lista=acessoService.findDescricao(acesso);
		return new ResponseEntity<List<Acesso>>(lista,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping("**/buscarAcessos")
    public ResponseEntity<List<Acesso>>buscarAcessos(){
		List<Acesso> acessos =acessoService.buscarAcessos();
		return new ResponseEntity<List<Acesso>>(acessos,HttpStatus.OK);
	}
	}
	

