package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.lojavitual.model.ItemVendaLoja;
import br.com.lojavitual.model.VendaCompraLojaVirtual;

public class EnvioEtiquetaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String service;
	private String agency;
	private FromEnvioEtiquetaDTO from = new FromEnvioEtiquetaDTO();
	private ToEnvioEtiquetaDTO to = new ToEnvioEtiquetaDTO();
	private List<ProdutcsEnvioEtiquetaDTO> products = new ArrayList<ProdutcsEnvioEtiquetaDTO>();
	private List<VolumesEnvioEtiquetaDTO> volumes = new ArrayList<VolumesEnvioEtiquetaDTO>();
	private OptionsEnvioEtiquetaDTO options = new OptionsEnvioEtiquetaDTO();

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public FromEnvioEtiquetaDTO getFrom() {
		return from;
	}

	public void setFrom(FromEnvioEtiquetaDTO from) {
		this.from = from;
	}

	public ToEnvioEtiquetaDTO getTo() {
		return to;
	}

	public void setTo(ToEnvioEtiquetaDTO to) {
		this.to = to;
	}

	public List<ProdutcsEnvioEtiquetaDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProdutcsEnvioEtiquetaDTO> products) {
		this.products = products;
	}

	public List<VolumesEnvioEtiquetaDTO> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<VolumesEnvioEtiquetaDTO> volumes) {
		this.volumes = volumes;
	}

	public OptionsEnvioEtiquetaDTO getOptions() {
		return options;
	}

	public void setOptions(OptionsEnvioEtiquetaDTO options) {
		this.options = options;
	}
	public EnvioEtiquetaDTO converter(VendaCompraLojaVirtual compraLojaVirtual) {
		EnvioEtiquetaDTO envioEtiquetaDTO = new EnvioEtiquetaDTO();

		envioEtiquetaDTO.setService(compraLojaVirtual.getServicoTransportadora());
		envioEtiquetaDTO.setAgency("49");
		envioEtiquetaDTO.getFrom().setName(compraLojaVirtual.getEmpresa().getNome());
		envioEtiquetaDTO.getFrom().setPhone(compraLojaVirtual.getEmpresa().getTelefone());
		envioEtiquetaDTO.getFrom().setEmail(compraLojaVirtual.getEmpresa().getEmail());
		envioEtiquetaDTO.getFrom().setDocument(compraLojaVirtual.getEmpresa().getCpf_resposavel());
		envioEtiquetaDTO.getFrom().setCompany_document(compraLojaVirtual.getEmpresa().getCnpj());
		envioEtiquetaDTO.getFrom().setState_register(compraLojaVirtual.getEmpresa().getInscEstadual());
		envioEtiquetaDTO.getFrom().setAddress(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getRuaLogra());
		envioEtiquetaDTO.getFrom().setComplement(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getComplemento());
		envioEtiquetaDTO.getFrom().setNumber(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getNumero());
		envioEtiquetaDTO.getFrom().setDistrict(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getBairro());
		envioEtiquetaDTO.getFrom().setCity(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getCidade());
		envioEtiquetaDTO.getFrom().setCountry_id(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getUf());
		envioEtiquetaDTO.getFrom().setPostal_code(compraLojaVirtual.getEmpresa().getEnderecos().get(0).getCep());
		envioEtiquetaDTO.getFrom().setNote("Não há");


		envioEtiquetaDTO.getTo().setName(compraLojaVirtual.getPessoa().getNome());
		envioEtiquetaDTO.getTo().setPhone(compraLojaVirtual.getPessoa().getTelefone());
		envioEtiquetaDTO.getTo().setEmail(compraLojaVirtual.getPessoa().getEmail());
		envioEtiquetaDTO.getTo().setDocument(compraLojaVirtual.getPessoa().getCpf());
		envioEtiquetaDTO.getTo().setCompany_document(compraLojaVirtual.getPessoa().getEmpresa().getCnpj());
		envioEtiquetaDTO.getTo().setState_register(compraLojaVirtual.getPessoa().getCpf());
		envioEtiquetaDTO.getTo().setAddress(compraLojaVirtual.getPessoa().enderecoEntrega().getRuaLogra());
		envioEtiquetaDTO.getTo().setComplement(compraLojaVirtual.getPessoa().enderecoEntrega().getComplemento());
		envioEtiquetaDTO.getTo().setNumber(compraLojaVirtual.getPessoa().enderecoEntrega().getNumero());
		envioEtiquetaDTO.getTo().setDistrict(compraLojaVirtual.getPessoa().enderecoEntrega().getBairro());
		envioEtiquetaDTO.getTo().setCity(compraLojaVirtual.getPessoa().enderecoEntrega().getCidade());
		envioEtiquetaDTO.getTo().setState_abbr(compraLojaVirtual.getPessoa().enderecoEntrega().getEstado());
		envioEtiquetaDTO.getTo().setCountry_id(compraLojaVirtual.getPessoa().enderecoEntrega().getUf());
		envioEtiquetaDTO.getTo().setPostal_code(compraLojaVirtual.getPessoa().enderecoEntrega().getCep());
		envioEtiquetaDTO.getTo().setNote("Não há");

		List<ProdutcsEnvioEtiquetaDTO> products = new ArrayList<ProdutcsEnvioEtiquetaDTO>();
		List<VolumesEnvioEtiquetaDTO> volumes = new ArrayList<VolumesEnvioEtiquetaDTO>();
		for (ItemVendaLoja itemVendaLoja : compraLojaVirtual.getItemVendaLojas()) {
			
			VolumesEnvioEtiquetaDTO dtov = new VolumesEnvioEtiquetaDTO();
			ProdutcsEnvioEtiquetaDTO dto = new ProdutcsEnvioEtiquetaDTO();
			
			dtov.setHeight(itemVendaLoja.getProduto().getAltura().toString());
			dtov.setLength(itemVendaLoja.getProduto().getProfundidade().toString());
			dtov.setWeight(itemVendaLoja.getProduto().getPeso().toString());
			dtov.setWidth(itemVendaLoja.getProduto().getLargura().toString());
			
			
			dto.setName(itemVendaLoja.getProduto().getNome());
			dto.setQuantity(itemVendaLoja.getQuantidade().toString());
			dto.setUnitary_value(String.valueOf(itemVendaLoja.getProduto().getValorVenda().doubleValue()));
			products.add(dto);
			
			volumes.add(dtov);
		}
		envioEtiquetaDTO.setProducts(products);
		envioEtiquetaDTO.setVolumes(volumes);
		envioEtiquetaDTO.getOptions().setInsurance_value(String.valueOf(compraLojaVirtual.getValorTotal()));
		envioEtiquetaDTO.getOptions().setReceipt(false);
		envioEtiquetaDTO.getOptions().setOwn_hand(false);
		envioEtiquetaDTO.getOptions().setReverse(false);
		envioEtiquetaDTO.getOptions().setNon_commercial(false);
		envioEtiquetaDTO.getOptions().getInvoice().setKey(compraLojaVirtual.getNotaFiscalVenda().getNumero());
		envioEtiquetaDTO.getOptions().setPlatform(compraLojaVirtual.getEmpresa().getNomeFastasia());
		
		TagsEnvioEtiquetaDTO dtoTagEnvio = new TagsEnvioEtiquetaDTO();
		dtoTagEnvio.setTag("Identificação do pedido na plataforma, exemplo:" + compraLojaVirtual.getId());
		dtoTagEnvio.setUrl(null);
		envioEtiquetaDTO.getOptions().getTags().add(dtoTagEnvio);
		
		return envioEtiquetaDTO;
	}
}
