package br.gov.ba.inema.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.inema.model.Endereco;
import br.gov.ba.inema.service.EnderecoService;
import br.gov.ba.inema.service.exception.ObjectNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Servico de Endereços" })
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/api/endereco")
	@ApiOperation(value = "Buscar Endereço por CEP", response = Endereco.class,  notes = "Busca o Endereço a partir de um CEP")
	public Endereco getEndereco(@RequestParam String cep) throws ObjectNotFoundException {
		return enderecoService.getEndereco(cep);
	}
	
	@GetMapping("/api/endereco/{id}")
	@ApiOperation(value = "Buscar Endereço por id", response = Endereco.class,  notes = "Busca o Endereço a partir de um ID")
	public Endereco findOne(@PathVariable(name = "id") Integer id) throws ObjectNotFoundException {
		return enderecoService.findOne(id);
	}
	
}
