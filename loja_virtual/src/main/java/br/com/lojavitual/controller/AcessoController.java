package br.com.lojavitual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Acesso;
import br.com.lojavitual.repository.AcessoRepository;
import br.com.lojavitual.service.AcessoService;

@Controller
@RestController
public class AcessoController {

	@Autowired
	AcessoService acessoService;

	@Autowired
	AcessoRepository acessoRepository;
	
	@ResponseBody
	@PostMapping(value = "**/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody @Valid Acesso acesso) throws ExceptionMentoriaJava {
     if(acesso.getId() == null) {
    	 Acesso descricao = acessoService.findDescricao(acesso.getDescricao());
    	 if(descricao != null ) {
    		 throw new ExceptionMentoriaJava("Usuario já cadastrado : "+ descricao);
    	 }
     }
		Acesso acessoSalvo = acessoService.salvar(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deletarAcesso")
	public ResponseEntity<?> deletarAcesso(@RequestBody Acesso acesso) {

		acessoService.deletar(acesso.getId());
		return new ResponseEntity<>("deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deletarAcessoPorId/{id}")
	public ResponseEntity<?> deletarAcessoPorId(@PathVariable Long id) {

		acessoService.deletarPorId(id);
		return new ResponseEntity<>("deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarAcesso")
	public ResponseEntity<List<Acesso>> buscarAcesso(@RequestBody Acesso acesso) throws ExceptionMentoriaJava {

		List<Acesso> lista = acessoService.findDescricoes(acesso);
		if(lista.isEmpty()) {
			throw new ExceptionMentoriaJava("Não foi encontrado nunhum registro");
		}
		return new ResponseEntity<List<Acesso>>(lista, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/buscarAcessos")
	public ResponseEntity<List<Acesso>> buscarAcessos() {
		List<Acesso> acessos = acessoService.buscarAcessos();
		return new ResponseEntity<List<Acesso>>(acessos, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarAcessoPorId/{id}")
	public ResponseEntity<Acesso> buscarAcesso(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

		Acesso acesso = acessoRepository.findById(id).orElse(null);
		if (acesso == null) {
			throw new ExceptionMentoriaJava("Usuario nao localizado :" +id);
		}
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
	}
}
