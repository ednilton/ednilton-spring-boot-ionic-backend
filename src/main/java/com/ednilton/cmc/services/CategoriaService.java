package com.ednilton.cmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ednilton.cmc.domain.Categoria;
import com.ednilton.cmc.repositories.CategoriaRepository;
import com.ednilton.cmc.services.exceptions.DataIntegrityException;
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

	/**
	 * Method Search one 
	 * 
	 * @param id
	 * @return
	 */
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	/**
	 * Method Insert 
	 * @param obj
	 * @return
	 */
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	/**
	 * Method Update
	 */
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
