package br.foo.pa.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.foo.pa.domain.Venda;


@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>  {

	/*public List<Venda> findAll() {

		Venda venda1 = new Venda(1L, "Arroz", "Fazenda",10.00F);
		Venda venda2 = new Venda(2L, "Feijão", "Booll",5.00F);
		Venda venda3 = new Venda(3L, "Refrigerante", "Jesus",6.50F);
		Venda venda4 = new Venda(4L, "Café", "Pilão",6.00F);
		Venda venda5 = new Venda(5L, "Suco", "Caju",4.00F);
		Venda venda6 = new Venda(6L, "Leite", "Do bom",8.50F);
		Venda venda7 = new Venda(7L, "Leite", "Itabe",6.50F);

		List<Venda> vendas = Arrays.asList(venda1, venda2, venda3, venda4, venda5, venda6, venda7);

		return vendas;
	}
	 */
}
