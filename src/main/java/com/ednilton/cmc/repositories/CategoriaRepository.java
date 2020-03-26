package com.ednilton.cmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednilton.cmc.domain.Categoria;

/**
 * Camada de acesso a Dados do tipo Categoria
 * @author ednilton
 *
 */

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
