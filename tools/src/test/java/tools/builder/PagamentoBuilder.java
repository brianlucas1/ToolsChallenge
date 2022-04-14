package tools.builder;

import tools.sofware.main.dto.DadosTransacaoDTO;
import tools.sofware.main.dto.DescricaoDTO;
import tools.sofware.main.dto.FormaPagamentoDTO;
import tools.sofware.main.enums.TipoEnum;
import tools.sofware.main.model.DadosTransacaoModel;
import tools.sofware.main.model.DescricaoModel;
import tools.sofware.main.model.FormaPagamentoModel;
import tools.sofware.main.model.TransacaoModel;

public class PagamentoBuilder {

	private DadosTransacaoModel pagamento;
	
	private PagamentoBuilder(){	}

	public static  PagamentoBuilder umPagamento (){
		
		PagamentoBuilder builder = new PagamentoBuilder();
		DadosTransacaoModel dados = new DadosTransacaoModel();
		TransacaoModel trans = new TransacaoModel();
		DescricaoModel desc = new DescricaoModel();
		FormaPagamentoModel pag = new FormaPagamentoModel();
		
		trans.setId("1234567");
		trans.setCartao("1236666");
		
		desc.setDataHora("");
		desc.setEstabelecimento("teste");
		desc.setValor(5.0);
		desc.setNsu("12345");
		desc.setStatus("A VISTA");
		desc.setCodigoAutorizacao("12345");
		
		pag.setParcelas("2");
		pag.setTipo(TipoEnum.AVISTA);
		
		trans.setDescricao(desc);
		trans.setFormaPagamento(pag);
		
		builder.pagamento = new DadosTransacaoModel();		
		builder.pagamento.setTransacao(trans);
		
		return builder;
		
	}
	
	public static  PagamentoBuilder umPagamentoComValorZero (){
		
		PagamentoBuilder builder = new PagamentoBuilder();
		DadosTransacaoModel dados = new DadosTransacaoModel();
		TransacaoModel trans = new TransacaoModel();
		DescricaoModel desc = new DescricaoModel();
		FormaPagamentoModel pag = new FormaPagamentoModel();
		
		trans.setId("1234567");
		trans.setCartao("1236666");
		
		desc.setDataHora("");
		desc.setEstabelecimento("teste");
		desc.setValor(0.0);
		desc.setNsu("12345");
		desc.setStatus("A VISTA");
		desc.setCodigoAutorizacao("12345");
		
		pag.setParcelas("2");
		pag.setTipo(TipoEnum.AVISTA);
		
		trans.setDescricao(desc);
		trans.setFormaPagamento(pag);
		
		builder.pagamento = new DadosTransacaoModel();		
		builder.pagamento.setTransacao(trans);
		
		return builder;
		
	}
	
	public static  PagamentoBuilder umPagamentoCancelado (){
		
		PagamentoBuilder builder = new PagamentoBuilder();
		DadosTransacaoModel dados = new DadosTransacaoModel();
		TransacaoModel trans = new TransacaoModel();
		DescricaoModel desc = new DescricaoModel();
		FormaPagamentoModel pag = new FormaPagamentoModel();
		
		trans.setId("1234567");
		trans.setCartao("1236666");
		
		desc.setDataHora("");
		desc.setEstabelecimento("teste");
		desc.setValor(5.0);
		desc.setStatus("CANCELADO");
		
		pag.setParcelas("2");
		pag.setTipo(TipoEnum.AVISTA);
		
		trans.setDescricao(desc);
		trans.setFormaPagamento(pag);
		
		builder.pagamento = new DadosTransacaoModel();		
		builder.pagamento.setTransacao(trans);
		
		return builder;
		
	}
	
	
	public DadosTransacaoModel agora(){
		return pagamento;
	}
	
}
