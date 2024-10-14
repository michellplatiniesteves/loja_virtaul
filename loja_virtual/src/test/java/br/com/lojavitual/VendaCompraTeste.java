package br.com.lojavitual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lojavitual.repository.VendaCompraLojaVirtualRepository;

@SpringBootTest(classes = LojaVirtualApplication.class)
public class VendaCompraTeste {
	@Autowired
	VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;

	@Test
	public void ImprimirEtiqueta() {
		vendaCompraLojaVirtualRepository.updateEtiqueta("9cfcc4f8-263c-4cba-ad03-8e94317097a0", 27L);
	}
}
