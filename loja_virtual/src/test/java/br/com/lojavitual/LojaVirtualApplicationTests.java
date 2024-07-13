package br.com.lojavitual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lojavitual.controller.AcessoController;
import br.com.lojavitual.model.Acesso;
import br.com.lojavitual.repository.AcessoRepository;
import br.com.lojavitual.service.AcessoService;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests extends TestCase{
	
    @Autowired
	AcessoService acessoService;
    @Autowired
	AcessoRepository acessoRepository;
    @Autowired
    AcessoController acessoController;
    @Autowired
    private WebApplicationContext wac;
	@Test
	public void CadastraApiMockAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder mvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac) ;
		MockMvc mockMvc = mvcBuilder.build();
		Acesso acesso = new Acesso(); 
		acesso.setDescricao("ROLE_ENGENHEIRO");
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions resultActions =mockMvc.
				                    perform(MockMvcRequestBuilders.post("/salvarAcesso")
				                    .content(mapper.writeValueAsString(acesso))
				                    .accept(MediaType.APPLICATION_JSON)
				                    .contentType(MediaType.APPLICATION_JSON)); 
		
		System.out.println("retorno da api :"+ resultActions.andReturn().getResponse().getContentAsString());
	}
	@Test
	public void DeletarApiMockAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder mvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac) ;
		MockMvc mockMvc = mvcBuilder.build();
		Acesso acesso = new Acesso(); 
		acesso.setDescricao("ROLE_SUPERVISOR");
        acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions resultActions =mockMvc.
				                    perform(MockMvcRequestBuilders.delete("/deletarAcesso")
				                    .content(mapper.writeValueAsString(acesso))
				                    .accept(MediaType.APPLICATION_JSON)
				                    .contentType(MediaType.APPLICATION_JSON)); 
		
		System.out.println("retorno da api :"+ resultActions.andReturn().getResponse().getContentAsString());
	}
	@Test
	public void DeletarPrIdApiMockAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder mvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac) ;
		MockMvc mockMvc = mvcBuilder.build();
		Acesso acesso = new Acesso(); 
		acesso.setDescricao("ROLE_SUPERVISORES");
        acessoRepository.save(acesso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions resultActions =mockMvc.
				                    perform(MockMvcRequestBuilders.delete("/deletarAcessoPorId/"+ acesso.getId())
				                    .content(mapper.writeValueAsString(acesso))
				                    .accept(MediaType.APPLICATION_JSON)
				                    .contentType(MediaType.APPLICATION_JSON)); 
		
		Acesso acessoretorno = mapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Acesso.class); 
		System.out.println("retorno da api :"+ resultActions.andReturn().getResponse().getContentAsString());
		System.out.println("retorno da api :"+ acessoretorno.toString() );
	}
	@Test
	public void buscarListaApiMockAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder mvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac) ;
		MockMvc mockMvc = mvcBuilder.build();
		Acesso acesso = new Acesso(); 
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions resultActions =mockMvc.
				                    perform(MockMvcRequestBuilders.get("/buscarAcessos")
				                    .content(mapper.writeValueAsString(acesso))
				                    .accept(MediaType.APPLICATION_JSON)
				                    .contentType(MediaType.APPLICATION_JSON)); 
		
		List<Acesso> acessoretorno = mapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),new TypeReference <List<Acesso>>() {}); 
		System.out.println("retorno da api :"+ resultActions.andReturn().getResponse().getContentAsString());
		System.out.println("retorno da api :"+ acessoretorno.toString() );
	}
	
	@Test
	public void CadastraAcesso() throws ExceptionMentoriaJava {
		Acesso acesso = new Acesso(); 
		acesso.setDescricao("ROLE_GERENTE");
		acessoRepository.save(acesso);
		acesso=acessoController.salvarAcesso(acesso).getBody();
		
		assertEquals(true, acesso.getId()>0);
	}

}
