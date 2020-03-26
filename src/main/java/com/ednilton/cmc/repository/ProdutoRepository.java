package com.ednilton.cmc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednilton.cmc.domain.Produto;

/**
 * Camada de acesso a Dados do tipo Poduto
 * @author ednilton
 *
 */

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Integer>{

}
