package com.ednilton.cmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ednilton.cmc.domain.Categoria;
import com.ednilton.cmc.domain.Produto;
import com.ednilton.cmc.repositories.CategoriaRepository;
import com.ednilton.cmc.repository.ProdutoRepository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CmcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
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
	}
	

}
