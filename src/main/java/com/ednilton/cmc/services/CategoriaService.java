package com.ednilton.cmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednilton.cmc.domain.Categoria;
import com.ednilton.cmc.repositories.CategoriaRepository;
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
public class CategoriaService {

	// injection dependency or control inversion
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
