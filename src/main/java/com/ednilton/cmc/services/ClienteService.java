package com.ednilton.cmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.ednilton.cmc.domain.Cliente;
import com.ednilton.cmc.dto.ClienteDTO;
import com.ednilton.cmc.repositories.ClienteRepository;
import com.ednilton.cmc.services.exceptions.DataIntegrityException;
import com.ednilton.cmc.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;


/**
 * This class throws an exception if the object is not found.
 * It must be requested by the CategoryResources layer, the Rest layer.
 * 
 * @author ednilton
 * checked
 */

@Service
public class ClienteService {

	// injection dependency or control inversion
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}


		public Cliente update(Cliente obj) {
			Cliente newObj = find(obj.getId());
			updateData(newObj, obj);
			return repo.save(newObj);
		}

		public void delete(Integer id) {
			find(id);
			try {
				repo.deleteById(id);
			}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
			}
		}

		public List<Cliente> findAll() {
			return repo.findAll();
		}

		public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
		}

		public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		}

		private void updateData(Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
}
	

