package br.gov.ba.inema.util;


/**
 * Repositório de mensagens do sistema
 * 
 * @author tulio
 *
 */
public enum MensagemUtil {

	//!!Favor acrescentar as mensagens na ordém alfabética e no bloco correto!!
	
	//Mensagens de Sucesso Genéricas
	MSG_S02("MSG_S02", "Registro criado com sucesso!"),
	
	//Mensagens de Alerta Genéricas
	MSG_A01("MSG_A01", "Campo %s é de preenchimento obrigatório!"),
	MSG_A11("MSG_A11", "Dados inválidos."),
	MSG_A14("MSG_A14", "CEP não encontrado."),
	
	//Mensagens de Informações Genéricas
	MSG_I02("MSG_I02", "Nenhum registro encontrado!"),
	
	//Mensagens de Erros Genéricos
	MSG_E01("MSG_E01", "Erro interno no sistema!");
	

	private String msgSigla;
	private String msgDescricao;

	private MensagemUtil(String msgSigla, String descricao) {
		this.msgSigla = msgSigla;
		this.msgDescricao = descricao;
	}

	public String getMsgSigla() {
		return msgSigla;
	}

	public String getMsgDescricao() {
		return msgDescricao;
	}

	public static MensagemUtil toEnum(String msgSigla) {
		if (msgSigla == null) {
			return null;
		}

		for (MensagemUtil x : MensagemUtil.values()) {
			if (msgSigla.equals(x.getMsgSigla())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + msgSigla);
	}
}
