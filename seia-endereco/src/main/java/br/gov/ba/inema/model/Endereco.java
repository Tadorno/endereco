package br.gov.ba.inema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "endereco")
public class Endereco implements Serializable{
	
	public Endereco() {
	}
	
	public Endereco(Integer id) {
		this.id = id;
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sq_endereco", sequenceName = "sq_endereco", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_endereco")
	private Integer id;
	
	@Size(min = 0, max = 8, message = "Tamanho máximo do CEP é 8")
	private String cep;
	
	@Size(min = 0, max = 255, message = "Tamanho máximo do Logradouro é 255")
	private String logradouro;
	
	@Size(min = 0, max = 150, message = "Tamanho máximo do Povoado/Bairro é 150")
	private String bairro;
	
	@Size(min = 0, max = 150, message = "Tamanho máximo do Municipio é 150")
	private String municipio;
	
	@Size(min = 0, max = 2, message = "Tamanho máximo da UF é 2")
	private String uf;
	
	@JsonIgnore
	@ApiModelProperty(hidden=true)
	private short fonte;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_cadastro")
	@JsonIgnore
	@ApiModelProperty(hidden=true)
	private Date dataCadastro = new Date();
	
	@JsonIgnore
	@ApiModelProperty(hidden=true)
	private boolean excluido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtc_excluido")
	@JsonIgnore
	@ApiModelProperty(hidden=true)
	private Date dataExclusao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if(cep != null) {
			cep = cep.replaceAll("\\.", "").replaceAll("-", "");
		}
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public short getFonte() {
		return fonte;
	}

	public void setFonte(short fonte) {
		this.fonte = fonte;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
