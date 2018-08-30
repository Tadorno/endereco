package br.gov.ba.inema.service.sources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.ba.inema.model.Endereco;
import br.gov.ba.inema.model.enums.EstadoEnum;
import br.gov.ba.inema.model.enums.FonteEnum;
import br.gov.ba.inema.service.generic.EnderecoSource;

//@Component
@Order(value = 4)
public class EnderecoViacepRestSource implements EnderecoSource {

	private final String URL = "http://viacep.com.br/ws/%s/json";

	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Endereco getEndereco(String cep) {

		try {
			ResponseEntity<String> response = restTemplate
					.getForEntity(String.format(URL, cep), String.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				Endereco endereco = new Endereco();

				String jsonInString = response.getBody();
				try {
					EnderecoViacepTemplate obj = mapper.readValue(jsonInString, EnderecoViacepTemplate.class);

					endereco.setBairro(obj.getBairro());
					endereco.setCep(obj.getCep());
					endereco.setLogradouro(obj.getLogradouro());
					endereco.setMunicipio(obj.getLocalidade());
					endereco.setUf(EstadoEnum.toEnum(obj.getUf()).getUf());
					
					return endereco;
				} catch (IOException e) {
					return null;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	@Override
	public short getFonte() {
		return FonteEnum.OUTROS.getCod();
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class EnderecoViacepTemplate {

	private String bairro;

	private String localidade;

	private String logradouro;

	private String cep;

	private String uf;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
