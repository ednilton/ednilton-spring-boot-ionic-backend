package com.ednilton.cmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednilton.cmc.domain.Estado;

/**
 * Camada de acesso a Dados do tipo Estado
 * @author ednilton
 *
 */

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
