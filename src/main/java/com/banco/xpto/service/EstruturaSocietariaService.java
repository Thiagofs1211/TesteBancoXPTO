package com.banco.xpto.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.xpto.model.EstruturaSocietaria;
import com.banco.xpto.model.PessoaFisica;
import com.banco.xpto.model.PessoaJuridica;
import com.banco.xpto.repository.EstruturaSocietariaRepository;

@Service
public class EstruturaSocietariaService {

	@Autowired
	EstruturaSocietariaRepository estruturaRepository;
	
	//Logando erros
	private static final Logger logger = LoggerFactory.getLogger(EstruturaSocietariaService.class);
	
	public Double comprometimentoFinanceiro(EstruturaSocietaria estruturaSocietaria) throws Exception {
		verificaEstruturaInformada(estruturaSocietaria);
		List<Long> pessoasFisicaContabilizada = new ArrayList<>();
		List<Long> pessoasJuridicaContabilizada = new ArrayList<>();
		
		EstruturaSocietaria estrutura = estruturaRepository.findById(estruturaSocietaria.getId())
				.orElseThrow(() -> new Exception("Estrutura Sociétaria não encontrada"));
		
		Double valorTotal = estrutura.getPessoaPrincipal().getTotalBens();
		pessoasFisicaContabilizada.add(estrutura.getPessoaPrincipal().getId());
		
		//Poderia ser executado paralelamente os calculos e somar os dois no final
		//porem como não sei se era necessário paralelizar fiz sequencialmente mesmo por ser mais simples
		for(PessoaFisica pessoa : estrutura.getOutrasPessoasFisica()) {
			if(!pessoasFisicaContabilizada.contains(pessoa.getId())) {
				pessoasFisicaContabilizada.add(pessoa.getId());
				valorTotal += pessoa.getTotalBens();
			}
		}
		
		//Não entendi se precisa contabilizar os socios dos socios da estruturaSocietaria informada
		//caso fosse bastaria criar uma lista de socios para percorrer verificando os que ja foram somados na lista que ja existe
		for(PessoaJuridica pessoa : estrutura.getOutrasPessoasJuridicas()) {
			if(!pessoasJuridicaContabilizada.contains(pessoa.getId())) {
				pessoasJuridicaContabilizada.add(pessoa.getId());
				valorTotal += pessoa.getTotalBens();
			}
		}
		return valorTotal;
	}
	
	//Fiquei na duvida das verificações que deveriam ser feitas, como estou utilizando um ID e não CPF da estrutura
	//eu busco somente pelo ID informado e utilizo a partir dele, mas deixei duas validações simples
	//coloquei somente cpf porque sempre tera uma pessoa fisica
	private void verificaEstruturaInformada(EstruturaSocietaria estrutura) throws Exception {
		if(estrutura.getId() == null) {
			logger.error("Estrutura Societaria sem ID");
			throw new Exception("Estrutura Societaria sem ID");
		}
		if(estrutura.getPessoaPrincipal().getCpf().length() != 11 && !estrutura.getPessoaPrincipal().getCpf().matches("[+-]?\\d*(\\.\\d+)?")) {
			logger.error("CPF invalido");
			throw new Exception("CPF invalido");
		}
	}
}
