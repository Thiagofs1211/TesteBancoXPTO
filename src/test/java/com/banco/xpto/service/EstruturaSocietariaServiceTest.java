package com.banco.xpto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.banco.xpto.model.EstruturaSocietaria;
import com.banco.xpto.model.PessoaFisica;
import com.banco.xpto.model.PessoaJuridica;
import com.banco.xpto.repository.EstruturaSocietariaRepository;

@SpringBootTest
public class EstruturaSocietariaServiceTest {

	@InjectMocks
	EstruturaSocietariaService service;
	
	@Mock
	EstruturaSocietariaRepository dao;
	
	@Test
	public void calcularComprometimentoFinanceiro() throws Exception {
		EstruturaSocietaria estrutura = preencherEstrutura();
		
		Mockito.when(dao.findById(estrutura.getId())).thenReturn(Optional.of(estrutura));
		
		Double valor = service.comprometimentoFinanceiro(estrutura);
		
		assertThat(valor).isEqualTo(130.5);
	}
	
	public EstruturaSocietaria preencherEstrutura() {
		EstruturaSocietaria estrutura = new EstruturaSocietaria();
		estrutura.setId(1L);
		estrutura.setPessoaPrincipal(preencherPessoaFisica(1L, "11481095609", "Thiago", 10.5));
		estrutura.getOutrasPessoasFisica().add(preencherPessoaFisica(2L, "85878208008", "Pessoa", 20.0));
		estrutura.getOutrasPessoasJuridicas().add(preencherPessoaJuridica(1L, "00101134000107", "Empresa", 100.0));
		return estrutura;
	}
	
	public PessoaFisica preencherPessoaFisica(Long id, String cpf, String nome, Double valorBens) {
		PessoaFisica pessoa = new PessoaFisica();
		pessoa.setId(id);
		pessoa.setCpf(cpf);
		pessoa.setNome(nome);
		pessoa.setTotalBens(valorBens);
		return pessoa;
	}
	
	public PessoaJuridica preencherPessoaJuridica(Long id, String cnpj, String nome, Double valorBens) {
		PessoaJuridica pessoa = new PessoaJuridica();
		pessoa.setId(id);
		pessoa.setCnpj(cnpj);
		pessoa.setNome(nome);
		pessoa.setTotalBens(valorBens);
		return pessoa;
	}
}
