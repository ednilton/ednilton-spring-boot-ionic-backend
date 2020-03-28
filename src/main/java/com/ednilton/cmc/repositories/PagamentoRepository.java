package com.ednilton.cmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednilton.cmc.domain.Pagamento;

/**
 * Camada de acesso a Dados do tipo Pagamento
 * @author ednilton
 *
 */

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
