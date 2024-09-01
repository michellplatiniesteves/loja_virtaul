package br.com.lojavitual.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class VendaCompraLojaVirtualUtilitaria {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void deletarEncadeado(@Param ("id")Long id) {
    	String	value = "begin;"
		           + "update nota_fiscal_venda set venda_compra_loja_virt_id=null where venda_compra_loja_virt_id = "+ id +"; "
		           + "delete from nota_fiscal_venda  where venda_compra_loja_virt_id= "+ id +"; "
		           + "delete from item_venda_loja  where venda_compra_loja_virt_id = "+ id +";"
		           + "delete from status_rastreio  where venda_compra_loja_virt_id = "+ id +"; "
		           + "delete from vd_cp_loja_virt where id = "+ id +"; "
		           +"commit;";
		jdbcTemplate.execute(value);
	}
}
