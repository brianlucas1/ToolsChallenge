package tools.sofware.main.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Data
public class DadosTransacaoDTO {
	
	@NotEmpty (message = "{campo.cartao}")
	private String cartao;
	
	@NotEmpty(message = "{campo.id}")
	private String id;	
	
	@Valid
	private DescricaoDTO descricao;
	
	@Valid
	private FormaPagamentoDTO formaPagamento;

}
