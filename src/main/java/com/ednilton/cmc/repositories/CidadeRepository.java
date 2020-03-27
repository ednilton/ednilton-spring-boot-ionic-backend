package com.ednilton.cmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednilton.cmc.domain.Cidade;

/**
 * Camada de acesso a Dados do tipo Cidade
 * @author ednilton
 *
 */

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
