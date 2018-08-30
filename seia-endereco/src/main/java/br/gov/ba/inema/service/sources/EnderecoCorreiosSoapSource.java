package br.gov.ba.inema.service.sources;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.gov.ba.inema.model.Endereco;
import br.gov.ba.inema.model.enums.FonteEnum;
import br.gov.ba.inema.service.generic.EnderecoSource;

@Component
@Order(value = 1)
public class EnderecoCorreiosSoapSource implements EnderecoSource {

	@Override
	public Endereco getEndereco(String cep) {
		try {
			AtendeClienteService correiosService = new AtendeClienteService();
			
			EnderecoERP enderecoERP = correiosService.getAtendeClientePort().consultaCEP(cep);

			Endereco endereco = new Endereco();
			endereco.setCep(enderecoERP.getCep());
			endereco.setLogradouro(enderecoERP.getEnd());
			endereco.setBairro(enderecoERP.getBairro());
			endereco.setMunicipio(enderecoERP.getCidade());
			endereco.setUf(enderecoERP.getUf());
			
			return endereco;		
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public short getFonte() {
		return FonteEnum.CORREIOS.getCod();
	}
}
