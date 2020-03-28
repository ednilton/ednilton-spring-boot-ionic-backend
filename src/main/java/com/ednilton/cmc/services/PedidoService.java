package com.ednilton.cmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednilton.cmc.domain.Pedido;
import com.ednilton.cmc.repositories.PedidoRepository;
import com.ednilton.cmc.services.exceptions.ObjectNotFoundException;

import java.util.Optional;


/**
 * This class throws an exception if the object is not found.
 * It must be requested by the CategoryResources layer, the Rest layer.
 * 
 * @author ednilton
 * checked
 */

@Service
public class PedidoService {

	// injection dependency or control inversion
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
