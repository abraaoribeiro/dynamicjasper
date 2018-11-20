package br.foo.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.foo.pa.domain.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
