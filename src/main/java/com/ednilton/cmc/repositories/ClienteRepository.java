package com.ednilton.cmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednilton.cmc.domain.Cliente;

/**
 * Camada de acesso a Dados do tipo Cliente
 * @author ednilton
 *
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
