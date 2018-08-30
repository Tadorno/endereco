package br.gov.ba.inema.model.enums;

public enum FonteEnum {
	
	CORREIOS((short) 1), 
	LOCAL((short) 2),
	OUTROS((short) 99);
	
	private short cod;
	
	private FonteEnum(short cod) {
		this.cod = cod;
	}
	
	public short getCod() {
		return cod;
	}
	
	public static FonteEnum toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(FonteEnum x : FonteEnum.values()) {
			if (cod == x.getCod()) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
