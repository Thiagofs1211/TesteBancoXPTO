package com.banco.xpto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.banco.xpto.model.EstruturaSocietaria;
import com.banco.xpto.model.PessoaFisica;
import com.banco.xpto.service.EstruturaSocietariaService;

@SpringBootTest
public class EstruturaSocietariaControllerTest {
	
	@InjectMocks
	private EstruturaSocietariaController controller;
	
	@Mock
	EstruturaSocietariaService service;

	@Test
	public void calcularComprometimentoFinanceiro() throws Exception {
		
		EstruturaSocietaria estrutura = preencherEstrutura();
		
		Mockito.when(service.comprometimentoFinanceiro(estrutura)).thenReturn(1000.0);
	
		Double valor = controller.comprometimentoFinanceiro(estrutura);
		
		assertThat(valor).isEqualTo(1000.0);
	}
	
	public EstruturaSocietaria preencherEstrutura() {
		EstruturaSocietaria estrutura = new EstruturaSocietaria();
		PessoaFisica pessoa = new PessoaFisica();
		estrutura.setId(1L);
		estrutura.setPessoaPrincipal(pessoa);
		return estrutura;
	}
}
