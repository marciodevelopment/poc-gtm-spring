package br.org.curitiba.ici.gtm.repositorys;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;

public interface AgenciaRepository extends JpaRepository<AgenciaEntity, Integer> {

	@EntityGraph(attributePaths = {"pessoa", "banco"})
	List<AgenciaEntity> findByPessoa_nomePessoaStartingWithOrderByPessoa_nomePessoaAsc(String nomePessoa, Pageable pageable);

}
