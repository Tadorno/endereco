package br.gov.ba.inema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.ba.inema.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	
	@Query("SELECT e FROM #{#entityName} e WHERE e.excluido=false "
			+ "AND e.cep = ?1 "
			+ "ORDER BY e.fonte asc, e.dataCadastro desc")
	public List<Endereco> findByCep(String cep);
	
	@Query("SELECT e FROM #{#entityName} e WHERE e.excluido=false "
			+ "AND e.cep = ?1 "
			+ "AND e.logradouro = ?2 "
			+ "AND e.bairro = ?3 "
			+ "AND e.uf = ?4 "
			+ "AND e.municipio = ?5")
	public Endereco findEndereco(String cep, String logradouro, String bairro, String uf, String municipio);
}
