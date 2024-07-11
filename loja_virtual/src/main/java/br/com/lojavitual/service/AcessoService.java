package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.Acesso;
import br.com.lojavitual.repository.AcessoRepository;

@Service
public class AcessoService {
	
    @Autowired
	private AcessoRepository acessoRepository;
    
    public Acesso salvar(Acesso acesso) {
    	return acessoRepository.save(acesso);
    }
    public void deletar(Long id) {
     acessoRepository.deleteById(id);
    }
    public void deletarPorId(Long id) {
        acessoRepository.deleteById(id);
       }
    public List<Acesso> findDescricao(Acesso acesso){
    	List<Acesso> lista = acessoRepository.buscarAcessoDescricao(acesso.getDescricao());
		return lista;
    	
    }
    
    public List<Acesso>buscarAcessos(){
    	List<Acesso>acessos = acessoRepository.findAll();
    	return acessos;
    }
}
