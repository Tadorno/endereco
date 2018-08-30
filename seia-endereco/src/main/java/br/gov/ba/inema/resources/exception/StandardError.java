package br.gov.ba.inema.resources.exception;

import java.io.Serializable;

/**
 * Helper de erro padrão para a grande maioria das exceções do sistema
 * 
 * @author tulio
 *
 */
public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private String msg;
	
	private String cod;
	
	private Long timeStamp;
	
	private String detalhe;
	
	public StandardError(Integer status, String codError, String msgError, Long timeStamp) {
		super();
		this.status = status;
		this.cod = codError;
		this.msg = msgError;
		this.timeStamp = timeStamp;
	}
	
	public StandardError(Integer status, String codError, String msgError, Long timeStamp, String detalhe) {
		super();
		this.status = status;
		this.cod = codError;
		this.msg = msgError;
		this.timeStamp = timeStamp;
		this.detalhe = detalhe;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}
	
}
