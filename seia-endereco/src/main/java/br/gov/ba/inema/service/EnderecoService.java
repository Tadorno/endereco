package br.gov.ba.inema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.inema.model.Endereco;
import br.gov.ba.inema.repository.EnderecoRepository;
import br.gov.ba.inema.service.exception.ObjectNotFoundException;
import br.gov.ba.inema.service.generic.EnderecoSource;

@Service
public class EnderecoService {
		
	@Autowired
	private List<EnderecoSource> sources;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	/**
	 * Retorna o endereço caso seja encontrado ou lança uma exceção se não for identificado
	 * 
	 * @param cep
	 * @return Endereco
	 * @throws ObjectNotFoundException
	 */
	public Endereco getEndereco(String cep) throws ObjectNotFoundException{
		if(cep == null) {
			throw new IllegalArgumentException("cep == null");
		}
		
		cep = filtrarCep(cep);
		
		if(cep.length() != 8) {
			throw new IllegalArgumentException("Tamanho de cep inválido.");
		}
		
		Endereco endereco = null;
		for(EnderecoSource source : sources) {
			endereco = source.getEndereco(cep);
			if(endereco != null) {
				endereco.setFonte(source.getFonte());
				break;
			}
		}
		
		//Se não foi encontrado nenhum endereço lança exceção
		if(endereco == null) {
			throw new ObjectNotFoundException(ObjectNotFoundException.MSGE001);
		}
		
		/**
		 * Se o idEndereco for null, então o endereco veio de uma fonte diferente da base local,
		 * neste caso deve-se atualizar o registro
		 * 
		 */
		if(endereco.getId() == null) {
			endereco = this.getEndereco(endereco);
		}
				
		return endereco;
	}
	
	/**
	 * Este método recebe o endereço e retorna o endereço com a informação do id,
	 * caso não exista o endereço no banco, o sistema cadastra e retorna o novo registro.
	 * 
	 * @param endereco
	 * @return Endereco
	 */
	public Endereco getEndereco(Endereco endereco) throws ObjectNotFoundException{
		Endereco result = enderecoRepository.findEndereco(
				filtrarCep(endereco.getCep())
				, endereco.getLogradouro()
				, endereco.getBairro() 
				, endereco.getUf()
				, endereco.getMunicipio());
		
		if(result == null) {
			result = enderecoRepository.save(endereco);
		}
		
		if(result == null) {
			throw new ObjectNotFoundException("Não foi possível encontrar o Endereço");
		}
		return result;
	}
	
	public Endereco findOne(Integer id) throws ObjectNotFoundException {
		Endereco entidade = this.enderecoRepository.findOne(id);
		if (entidade == null) {
			throw new ObjectNotFoundException(ObjectNotFoundException.MSGE001);
		}
		return entidade;
	}
	
	private String filtrarCep(String cep) {
		return cep.replaceAll("\\.", "").replaceAll("-", "");
	}
}
