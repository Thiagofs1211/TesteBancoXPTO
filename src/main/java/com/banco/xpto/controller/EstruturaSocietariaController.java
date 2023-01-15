package com.banco.xpto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.xpto.model.EstruturaSocietaria;
import com.banco.xpto.service.EstruturaSocietariaService;

@RestController
@RequestMapping(path="/estrutura-societaria")
public class EstruturaSocietariaController {

	@Autowired
	EstruturaSocietariaService estruturaService;
	
//	Mock
//	@GetMapping(path = "/compromentimento-financeiro/{id}")
	@GetMapping(path = "/compromentimento-financeiro")
	public Double comprometimentoFinanceiro(EstruturaSocietaria estrutura) throws Exception {
//		Mock
//		PessoaFisica pessoa = new PessoaFisica();
//		pessoa.setCpf("11481095609");
//		EstruturaSocietaria estrutura = new EstruturaSocietaria();
//		estrutura.setId(id);
//		estrutura.setPessoaPrincipal(pessoa);
		return estruturaService.comprometimentoFinanceiro(estrutura);
	}
}
