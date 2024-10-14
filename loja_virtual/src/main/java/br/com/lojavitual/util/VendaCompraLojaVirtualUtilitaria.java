package br.com.lojavitual.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.lojavitual.DTO.RelatorioProdutosDTO;
import br.com.lojavitual.DTO.RelatorioProdutosEstoqueMinimoDTO;

@Service
public class VendaCompraLojaVirtualUtilitaria {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void deletarEncadeado(@Param("id") Long id) {
		String value = "begin;"
				+ "update nota_fiscal_venda set venda_compra_loja_virt_id=null where venda_compra_loja_virt_id = " + id
				+ "; " + "delete from nota_fiscal_venda  where venda_compra_loja_virt_id= " + id + "; "
				+ "delete from item_venda_loja  where venda_compra_loja_virt_id = " + id + ";"
				+ "delete from status_rastreio  where venda_compra_loja_virt_id = " + id + "; "
				+ "delete from vd_cp_loja_virt where id = " + id + "; " + "commit;";
		jdbcTemplate.execute(value);
	}

	public void exclusaoLogica(@Param("id") Long id) {
		String value = "begin;"
				+ "update vd_cp_loja_virt set excluido=true where id ="+id+"; " 
				+ "commit;";
		jdbcTemplate.execute(value);
	}
	public void ativacaoLogica(@Param("id") Long id) {
		String value = "begin;"
				+ "update vd_cp_loja_virt set excluido=false where id ="+id+"; " 
				+ "commit;";
		jdbcTemplate.execute(value);
	}
	
	public List<RelatorioProdutosDTO> relatorioProdutos(RelatorioProdutosDTO relatorioProdutosDTO){
		String sql = "select p.id as id, "
				+ "       p.nome as nome, "
				+ "       p.valor_venda as valorVenda, "
				+ "       ntp.quantidade as quantidadeComprada,"
				+ "       pj.id as idFornecedor,"
				+ "       pj.nome as nomeFornecedor,"
				+ "       cfc.data_compra as dataCompra, "
				+ "       p.qtd_estoque as quantidadeEstoque "
				+ " from nota_fiscal_compra as cfc "
				+ " inner join nota_item_produto as ntp on ntp.nota_fiscal_compra_id=cfc.id "
				+ " inner join produto as p on p.id=ntp.produto_id "
				+ " inner join pessoa_juridica as pj on pj.id=cfc.empresa_id "
				+ " where ";
				sql+=" cfc.data_compra>= '"+relatorioProdutosDTO.getDataInicial()+"'and ";
				sql+=" cfc.data_compra<= '"+relatorioProdutosDTO.getDataFinal()+"' ";
				if(!relatorioProdutosDTO.getId().isEmpty()) {
					sql+=" and p.id= '"+relatorioProdutosDTO.getId()+"' ";
				}
				if(!relatorioProdutosDTO.getIdFornecedor().isEmpty()) {
					sql+=" and pj.id = '"+relatorioProdutosDTO.getIdFornecedor()+"' ";
				}
				if(!relatorioProdutosDTO.getNome().isEmpty()) {
					sql+=" and upper(p.nome) like upper('%"+relatorioProdutosDTO.getNome()+"')";
				}
		List<RelatorioProdutosDTO> resultado = new ArrayList<RelatorioProdutosDTO>();
		return resultado = jdbcTemplate.query(sql,new BeanPropertyRowMapper(RelatorioProdutosDTO.class));
	}
	public List<RelatorioProdutosEstoqueMinimoDTO> relatorioProdutosEstoqueMinimo(RelatorioProdutosEstoqueMinimoDTO relatorioProdutosEstoqueMinimoDTO){
		String sql = "select p.id as id, "
				+ "       p.nome as nome, "
				+ "       p.valor_venda as valorVenda, "
				+ "       ntp.quantidade as quantidadeComprada,"
				+ "       pj.id as idFornecedor,"
				+ "       pj.nome as nomeFornecedor,"
				+ "       cfc.data_compra as dataCompra, "
				+ "       p.qtd_estoque as quantidadeEstoque, "
				+ "       p.qtde_alerta_estoque as quantidadeEstoqueAlerta "
				+ " from nota_fiscal_compra as cfc "
				+ " inner join nota_item_produto as ntp on ntp.nota_fiscal_compra_id=cfc.id "
				+ " inner join produto as p on p.id=ntp.produto_id "
				+ " inner join pessoa_juridica as pj on pj.id=cfc.empresa_id "
				+ " where p.qtd_estoque <= p.qtde_alerta_estoque and ";
				sql+=" cfc.data_compra>= '"+relatorioProdutosEstoqueMinimoDTO.getDataInicial()+"'and ";
				sql+=" cfc.data_compra<= '"+relatorioProdutosEstoqueMinimoDTO.getDataFinal()+"' ";
				if(!relatorioProdutosEstoqueMinimoDTO.getId().isEmpty()) {
					sql+=" and p.id= '"+relatorioProdutosEstoqueMinimoDTO.getId()+"' ";
				}
				if(!relatorioProdutosEstoqueMinimoDTO.getIdFornecedor().isEmpty()) {
					sql+=" and pj.id = '"+relatorioProdutosEstoqueMinimoDTO.getIdFornecedor()+"' ";
				}
				if(!relatorioProdutosEstoqueMinimoDTO.getNome().isEmpty()) {
					sql+=" and upper(p.nome) like upper('%"+relatorioProdutosEstoqueMinimoDTO.getNome()+"')";
				}
		List<RelatorioProdutosEstoqueMinimoDTO> resultado = new ArrayList<RelatorioProdutosEstoqueMinimoDTO>();
		return resultado = jdbcTemplate.query(sql,new BeanPropertyRowMapper(RelatorioProdutosEstoqueMinimoDTO.class));
	}

}
