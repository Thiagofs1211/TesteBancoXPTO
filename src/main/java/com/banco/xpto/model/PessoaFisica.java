package com.banco.xpto.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

//Entidade que contem os atributos de uma pessoa fisica
@Entity
@Getter
@Setter
@Table(name = "TBL_PESSOA_FISICA")
public class PessoaFisica {

	@Id
	@Column(name = "ID_PESSOA_FISICA")
	private Long id;
	
	@Column(name = "CPF", length = 11)
	@NotNull
	private String cpf;
	
	@Column(name = "CONTABILIZACAO_TOTAL_BENS")
	private Double totalBens;
	
	@ManyToMany(mappedBy = "outrasPessoasFisica")
	private Set<EstruturaSocietaria> estrutura = new HashSet<>();
	
	@Column(name = "NOME", length = 50)
	private String nome;
	
	//Outros atributos que a pessoa Física possa ter
}
