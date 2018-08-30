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
@Order(value = 3)
public class EnderecoPostmanRestSource implements EnderecoSource {

	private final String URL = "http://api.postmon.com.br/v1/cep/%s";

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
					EnderecoPostmanTemplate obj = mapper.readValue(jsonInString, EnderecoPostmanTemplate.class);

					endereco.setBairro(obj.getBairro());
					endereco.setCep(obj.getCep());
					endereco.setLogradouro(obj.getLogradouro());
					endereco.setMunicipio(obj.getCidade());
					endereco.setUf(EstadoEnum.toEnum(obj.getEstado()).getUf());

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
class EnderecoPostmanTemplate {

	private String bairro;

	private String cidade;

	private String logradouro;

	private String cep;

	private String estado;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
