package br.gov.ba.inema.model.enums;

public enum EstadoEnum {

	BA("BA", "Bahia");

	private String uf;
	private String descricao;

	private EstadoEnum(String uf, String descricao) {
		this.uf = uf;
		this.descricao = descricao;
	}

	public String getUf() {
		return uf;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoEnum toEnum(String text) {
		if (text == null) {
			return null;
		}

		EstadoEnum estado;
		if(text.length() == 2) {
			estado = findByUf(text);
		}else {
			estado = findByDescricao(text);
		}
		
		return estado;
	}
	
	private static EstadoEnum findByUf(String uf) {
		for (EstadoEnum x : EstadoEnum.values()) {
			if (uf.equals(x.getUf())) {
				return x;
			}
		}
		return null;
	}
	
	private static EstadoEnum findByDescricao(String descricao) {
		for (EstadoEnum x : EstadoEnum.values()) {
			if (descricao.equals(x.getDescricao())) {
				return x;
			}
		}
		return null;
	}
}
