package br.org.curitiba.ici.gtm.repositorys;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
	List<PessoaEntity> findByNomePessoaStartingWith(String nomePessoa, Pageable pageable);
}
