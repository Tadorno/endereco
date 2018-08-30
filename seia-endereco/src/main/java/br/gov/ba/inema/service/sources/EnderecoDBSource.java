package br.gov.ba.inema.service.sources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.gov.ba.inema.model.Endereco;
import br.gov.ba.inema.model.enums.FonteEnum;
import br.gov.ba.inema.repository.EnderecoRepository;
import br.gov.ba.inema.service.generic.EnderecoSource;

@Component
@Order(value = 2)
public class EnderecoDBSource implements EnderecoSource {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public Endereco getEndereco(String cep) {
		List<Endereco> result = enderecoRepository.findByCep(cep);
		
		if(result == null || result.isEmpty()) {
			return null;
		}else {
			return enderecoRepository.findByCep(cep).get(0);
		}
	}
	
	@Override
	public short getFonte() {
		return FonteEnum.LOCAL.getCod();
	}

}
