package br.abraao.pa.repository;

import java.util.Arrays;
import java.util.List;

import br.abraao.pa.domain.Venda;

public class VendaRepository {

	public List<Venda> findAll() {

		Venda venda1 = new Venda(1L, "Arroz", "Fazenda");
		Venda venda2 = new Venda(2L, "Feijão", "Booll");
		Venda venda3 = new Venda(3L, "Refrigerante", "Jesus");
		Venda venda4 = new Venda(4L, "Café", "Pilão");
		Venda venda5 = new Venda(5L, "Suco", "Caju");
		Venda venda6 = new Venda(6L, "Leite", "Do bom");
		Venda venda7 = new Venda(7L, "Leite", "Itabe");

		List<Venda> vendas = Arrays.asList(venda1, venda2, venda3, venda4, venda5, venda6, venda7);

		return vendas;

	}
}
