package com.ednilton.cmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ednilton.cmc.domain.Categoria;
import com.ednilton.cmc.domain.Cidade;
import com.ednilton.cmc.domain.Cliente;
import com.ednilton.cmc.domain.Endereco;
import com.ednilton.cmc.domain.Estado;
import com.ednilton.cmc.domain.Produto;
import com.ednilton.cmc.domain.enums.TipoCliente;
import com.ednilton.cmc.repositories.CategoriaRepository;
import com.ednilton.cmc.repositories.CidadeRepository;
import com.ednilton.cmc.repositories.ClienteRepository;
import com.ednilton.cmc.repositories.EnderecoRepository;
import com.ednilton.cmc.repositories.EstadoRepository;
import com.ednilton.cmc.repositories.ProdutoRepository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CmcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository  clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	
	public static void main(String[] args) {
		SpringApplication.run(CmcApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00, "Dell I7"); 
		Produto p2 = new Produto(null, "Impressora", 600.00, "Hp DesckJet800"); 
		Produto p3 = new Produto(null, "Tonner", 80.00, "Hp4812"); 
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		// definindo os protudo na categoria 
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		/***********************************************************/
		
		// Instanciando Estado e Cidades
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		// Adicionando Estado adicionando cidades a sua lista.
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));// Estate SP adding Campinas, São Paulo 
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2, c3));
		
		/***********************************************************/
		
		Cliente cli1 = new Cliente(null, "Ednilton Rauh", "edrauh@gmail.com","70066655543", TipoCliente.PESSOAFISICA );
		cli1.getTelefones().addAll(Arrays.asList("414421707", "62996873909"));
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardins", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Mattos", "105", "Sala 800", "Centro", "387777012", cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
	

}
