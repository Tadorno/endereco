package br.gov.ba.inema.service.generic;

import br.gov.ba.inema.model.Endereco;

/**
 * Interface que padroniza os fonte de Endereços
 * @author tulio
 *
 */
public interface EnderecoSource {

	/**
	 * Retorna um objeto Endereco se for encontrado ou null se não for encontrado
	 * @param cpf
	 * @return Endereco
	 */
	public Endereco getEndereco(String cpf);
	
	/**
	 * Retorna a fonte do recurso
	 * @return
	 */
	public short getFonte();
}
