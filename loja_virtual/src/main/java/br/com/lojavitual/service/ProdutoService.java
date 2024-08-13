package br.com.lojavitual.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Produto;
import br.com.lojavitual.repository.PessoaJuridicaRepository;
import br.com.lojavitual.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;
	@Autowired
	ServiceSendEmail serviceSendEmail;

	public List<Produto> buscarTodosProdutos() {
		List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
		return produtos;
	}

	public Produto buscarProdutosPorId(Long id) {
		Produto produto = produtoRepository.findById(id).get();
		return produto;
	}

	public List<Produto> buscarProdutosNome(String nome) {
		List<Produto> produtos = produtoRepository.findByNome(nome);
		return produtos;
	}

	public void deletarProdutosPorId(Long id) {
		produtoRepository.deleteById(id);

	}

	public Produto salvarProdutos(Produto produto) throws ExceptionMentoriaJava, MessagingException, IOException {

		if (produto == null) {
			throw new ExceptionMentoriaJava("Informe um produto");
		}

		if (produto.getTipoUnidade() == null || produto.getTipoUnidade().isEmpty()) {
			throw new ExceptionMentoriaJava("Informe o Tipo Unidade");
		}
		if (produto.getEmpresa() == null || produto.getEmpresa().getId() < 0) {
			throw new ExceptionMentoriaJava("Informe a Empresa");
		}
		if (produto.getCategoriaProduto() == null || produto.getCategoriaProduto().getId() < 0) {
			throw new ExceptionMentoriaJava("Informe a Categoria do Produto");
		}
		if (produto.getMarcaProduto() == null || produto.getMarcaProduto().getId() < 0) {
			throw new ExceptionMentoriaJava("Informe a Marca do Produto");
		}
		if (produto.getId() == null
				&& produtoRepository.existeProdutoCadastrado(produto.getNome(), produto.getEmpresa().getId())) {
			throw new ExceptionMentoriaJava("Nome de produto ja cadastrado");
		}
		if (produto.getImagens() == null || produto.getImagens().isEmpty() || produto.getImagens().size() == 0) {
			throw new ExceptionMentoriaJava("Informe a Imagem ");
		}

		if (produto.getImagens().size() < 3) {
			throw new ExceptionMentoriaJava("Deve conter minimo 3 Imagem ");
		}
		if (produto.getImagens().size() > 6) {
			throw new ExceptionMentoriaJava("Deve conter maximo 6 Imagem ");
		}
		if (produto.getId() == null) {
			for (int i = 0; i < produto.getImagens().size(); i++) {
				produto.getImagens().get(i).setProduto(produto);
				produto.getImagens().get(i).setEmpresa(produto.getEmpresa());

				String base64Image = "";
				if (produto.getImagens().get(i).getImagemOriginal().contains("data:image")) {
					base64Image = produto.getImagens().get(i).getImagemOriginal().split(",")[1];
				} else {
					base64Image = produto.getImagens().get(i).getImagemOriginal();
				}
				byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
				if (bufferedImage != null) {
					int type = bufferedImage.getType() == 0 ? bufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
					int largura = Integer.parseInt("800");
					int altura = Integer.parseInt("600");

					BufferedImage redenrizadaImage = new BufferedImage(largura, altura, type);
					Graphics2D g = redenrizadaImage.createGraphics();
					g.drawImage(bufferedImage, 0, 0, largura, altura, null);
					g.dispose();

					ByteArrayOutputStream boas = new ByteArrayOutputStream();
					ImageIO.write(redenrizadaImage, "png", boas);

					String miniImage = "data:image/png;base64,"
							+ DatatypeConverter.printBase64Binary(boas.toByteArray());
					produto.getImagens().get(i).setImagemMiniatura(miniImage);

					bufferedImage.flush();
					redenrizadaImage.flush();
					boas.flush();
					boas.close();
				}
			}
		}
		produto = produtoRepository.save(produto);

		if (produto.getAlertaQtdeEstoque() && produto.getQtdEstoque() < 1) {
			StringBuilder html = new StringBuilder();
			html.append("Produto com estoque baixo : ").append(produto.getNome())
					.append(produto.getEmpresa().getNome());
			if (produto.getEmpresa().getEmail() != null) {
				serviceSendEmail.enviarEmail("Estoque baixo", html.toString(), produto.getEmpresa().getEmail());
			}
		}
		return produto;
	}
}
