package tools.sofware.main.model;

import lombok.Data;


@Data
public class TransacaoModel {
	
	private String id;	
	private String cartao;
	
	private DescricaoModel descricao;
	private FormaPagamentoModel formaPagamento;

}
