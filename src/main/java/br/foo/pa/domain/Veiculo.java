package br.foo.pa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Veiculo {

	@Id
	private Long id;
	private String marca;
	private String modelo;
	private String estado;
	private Float valor;
	private Float  km;
}
