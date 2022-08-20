package br.org.curitiba.ici.gtm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.curitiba.ici.gtm.entity.BancoEntity;

public interface BancoRepository extends JpaRepository<BancoEntity, Integer> {
}
