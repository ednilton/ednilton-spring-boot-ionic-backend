package com.ednilton.cmc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ednilton.cmc.domain.Cidade;
import com.ednilton.cmc.domain.Cliente;
import com.ednilton.cmc.domain.Endereco;
import com.ednilton.cmc.domain.enums.TipoCliente;
import com.ednilton.cmc.dto.ClienteDTO;
import com.ednilton.cmc.dto.ClienteNewDTO;
import com.ednilton.cmc.repositories.CidadeRepository;
import com.ednilton.cmc.repositories.ClienteRepository;
import com.ednilton.cmc.repositories.EnderecoRepository;
import com.ednilton.cmc.services.exceptions.DataIntegrityException;
import com.ednilton.cmc.services.exceptions.ObjectNotFoundException;


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
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEndereco());
		return obj;
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
			throw new DataIntegrityException("Não é possível excluir o cliente porque há pedidos relacionados");
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
	
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli  = new Cliente(null , objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = cidadeRepository.getOne(objDto.getCidadeId());
		Endereco end = new Endereco(null, objDto.getLogradouro(),objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEndereco().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!= null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!= null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
	

