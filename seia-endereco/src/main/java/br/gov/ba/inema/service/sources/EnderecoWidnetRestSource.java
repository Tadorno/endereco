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
@Order(value = 5)
public class EnderecoWidnetRestSource implements EnderecoSource {

	private final String URL = "http://apps.widenet.com.br/busca-cep/api/cep.json?code=%s";

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
					EnderecoWidnetTemplate obj = mapper.readValue(jsonInString, EnderecoWidnetTemplate.class);

					endereco.setBairro(obj.getDistrict());
					endereco.setCep(obj.getCode());
					endereco.setLogradouro(obj.getAddress());
					endereco.setMunicipio(obj.getAddress());
					endereco.setUf(EstadoEnum.toEnum(obj.getState()).getUf());
					
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
class EnderecoWidnetTemplate {

	private String state;

	private String city;

	private String address;

	private String code;

	private String district;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
}
