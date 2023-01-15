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

//Entidade que contem os atributos de uma pessoa juridica
@Entity
@Getter
@Setter
@Table(name = "TBL_PESSOA_JURIDICA")
public class PessoaJuridica {

	@Id
	@Column(name = "ID_PESSOA_JURIDICA")
	private Long id;
	
	@Column(name = "CNPJ", length = 14)
	@NotNull
	private String cnpj;
	
	@ManyToMany(mappedBy = "outrasPessoasJuridicas")
	private Set<EstruturaSocietaria> estrutura = new HashSet<>();
	
	@Column(name = "CONTABILIZACAO_TOTAL_BENS")
	private Double totalBens;
	
	@Column(name = "NOME")
	private String nome;
	
	//Outros atributos que uma pessoa juridica possa ter
}
