package com.banco.xpto.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ESTRUTURA_SOCIETARIA")
public class EstruturaSocietaria {

	@Id
	@Column(name = "ID_ESTRUTURA")
	private Long id;
	
	//Coloquei uma pessoa fisica separada como principal, pois sempre deve existir
	@ManyToOne
	@NotNull
	@JoinColumn(name = "PESSOA_FISICA_PRINCIPAL", referencedColumnName = "ID_PESSOA_FISICA")
	private PessoaFisica pessoaPrincipal;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "ESTRUTURA_PESSOA_FISICA", joinColumns = { @JoinColumn(name = "ID_ESTRUTURA") },
			inverseJoinColumns = { @JoinColumn(name = "ID_PESSOA_FISICA") })	
	private Set<PessoaFisica> outrasPessoasFisica = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "ESTRUTURA_PESSOA_JURIDICA", joinColumns = { @JoinColumn(name = "ID_ESTRUTURA") },
			inverseJoinColumns = { @JoinColumn(name = "ID_PESSOA_JURIDICA") })	
	private Set<PessoaJuridica> outrasPessoasJuridicas = new HashSet<>();
}
