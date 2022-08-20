package br.org.curitiba.ici.gtm.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.curitiba.ici.gtm.entity.BancoEntity;
import br.org.curitiba.ici.gtm.repository.BancoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BancoService {
	private final BancoRepository bancoRepository;
	
	public BancoEntity getReference(Integer codBanco) {
		return bancoRepository.getReferenceById(codBanco);
	}

	public Collection<BancoEntity> listAll() {
		return this.bancoRepository.findAll(Sort.by(Direction.ASC, "nomeBanco"));
	}

	public boolean notExists(BancoEntity banco) {
		return !bancoRepository.existsById(banco.getCodBanco());
	}

	public Optional<BancoEntity> findById(Integer codBanco) {
		return bancoRepository.findById(codBanco);
	}

}
