package br.com.lojavitual.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.DTO.CepDTO;
import br.com.lojavitual.DTO.ConsultaCnpjDTO;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.service.PessoaJuridicaService;
import br.com.lojavitual.util.Utilitaria;
import br.com.lojavitual.util.ValidaCNPJ;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PessoaJuridicaController {

	@Autowired
	PessoaJuridicaService pessoaJuridicaService;

	@Autowired
	Utilitaria utilitaria;

	@ResponseBody
	@PostMapping("**/PessoaJuridica/salvarPessoaJuridica")
	public ResponseEntity<PessoaJuridica> salvarPessoaJuridica(@RequestBody @Valid PessoaJuridica pessoaJuridica)
			throws ExceptionMentoriaJava, UnsupportedEncodingException {

		pessoaJuridica = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);

		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping("**/PessoaJuridica/deletarPessoaJuridica/{id}")
	public ResponseEntity<?> deletarPessoaJuridica(@PathVariable("id") Long id) {
		pessoaJuridicaService.deletarPessoaJuridica(id);
		return new ResponseEntity<>("deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/PessoaJuridica/buscarPessoaJuridicaPorId/{id}")
	public ResponseEntity<PessoaJuridica> buscarPessoaJuridicaPorId(@PathVariable("id") Long id) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaService.buscarPessoaJuridicaPorId(id);
		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/PessoaJuridica/listaPessoaJuridicaPorCnpj/{cnpj}")
	public ResponseEntity<List<PessoaJuridica>> listaPessoaJuridicaPorCnpj(@PathVariable("cnpj") String cnpj) {
		List<PessoaJuridica> listaPessoaJuridicaPorCnpj = pessoaJuridicaService.listaPessoaJuridicaPorCnpj(cnpj.trim().toUpperCase());
		return new ResponseEntity<List<PessoaJuridica>>(listaPessoaJuridicaPorCnpj, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/PessoaJuridica/listaPessoaJuridicaPorNome/{nome}")
	public ResponseEntity<List<PessoaJuridica>> listaPessoaJuridicaPorNome(@PathVariable("nome") String nome) {
		List<PessoaJuridica> listaPessoaJuridicaPorNome = pessoaJuridicaService.listaPessoaJuridicaPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<PessoaJuridica>>(listaPessoaJuridicaPorNome, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/PessoaJuridica/listaPessoaJuridicaPorNomeFastasia/{nomeFastasia}")
	public ResponseEntity<List<PessoaJuridica>> listaPessoaJuridicaPorNomeFastasia(
			@PathVariable("nomeFastasia") String nomeFastasia) {
		List<PessoaJuridica> listaPessoaJuridicaPorNomeFastasia = pessoaJuridicaService
				.listaPessoaJuridicaPorNomeFastasia(nomeFastasia.trim().toUpperCase());
		return new ResponseEntity<List<PessoaJuridica>>(listaPessoaJuridicaPorNomeFastasia, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/PessoaJuridica/buscarTodasPessoaJuridica")
	public ResponseEntity<List<PessoaJuridica>> buscarTodasPessoaJuridica() {
		List<PessoaJuridica> listapj = pessoaJuridicaService.buscarTodasPessoaJuridica();
		return new ResponseEntity<List<PessoaJuridica>>(listapj, HttpStatus.OK);
	}

	@ResponseBody
	@PutMapping("**/PessoaJuridica/atualizarPessoaJuridica")
	public ResponseEntity<PessoaJuridica> atualizarPessoaJuridica(@RequestBody @Valid PessoaJuridica pessoaJuridica)
			throws UnsupportedEncodingException, ExceptionMentoriaJava {
		pessoaJuridica = pessoaJuridicaService.atualizaPessoaJuridica(pessoaJuridica);
		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);

	}
	@ResponseBody
	@GetMapping("**/PessoaJuridica/consultaCnpjReceitaFederalWs/{cnpj}")
	public ResponseEntity<ConsultaCnpjDTO> consultaCnpjReceitaFederalWs(@PathVariable("cnpj")String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO =pessoaJuridicaService.consultaCnpjReceitaFederalWs(cnpj);
		return new ResponseEntity<ConsultaCnpjDTO>(consultaCnpjDTO,HttpStatus.OK);
	}
	

}
