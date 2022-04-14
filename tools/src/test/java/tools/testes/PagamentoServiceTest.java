package tools.testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tools.builder.PagamentoBuilder;
import tools.sofware.main.dto.PagamentoDTO;
import tools.sofware.main.excpetion.ValorPagamentoMenorQueZero;
import tools.sofware.main.model.DadosTransacaoModel;
import tools.sofware.main.service.PagamentoService;


public class PagamentoServiceTest {
	
	private PagamentoService service;
	
	@Before
	public void setup(){
		service = new PagamentoService();
	}
	
	@Test
	public void valorPagamentoMaiorQueZero() throws ValorPagamentoMenorQueZero{
		
		
		DadosTransacaoModel pag = PagamentoBuilder.umPagamento().agora();
		
		double valor =  service.validaValorPagamento(pag);
		
		assert(valor > 0);
	}
	
	@Test(expected = ValorPagamentoMenorQueZero.class)
	public void valorPagamentoMenorQueZero() throws ValorPagamentoMenorQueZero{
		
		
		DadosTransacaoModel pag = PagamentoBuilder.umPagamentoComValorZero().agora();
		
		double valor =  service.validaValorPagamento(pag);

	}
	
	@Test
	public void pagamentoCancelado(){
		
		DadosTransacaoModel pag = PagamentoBuilder.umPagamentoCancelado().agora();
		
		boolean cancelado = service.verificaPagamentoCancelado(pag.getTransacao().getDescricao().getStatus());
		
		
		Assert.assertTrue(cancelado);
		
	}
	

}
