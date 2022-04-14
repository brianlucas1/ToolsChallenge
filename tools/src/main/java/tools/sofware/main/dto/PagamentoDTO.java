package tools.sofware.main.dto;

import java.util.UUID;

import javax.validation.Valid;

import lombok.Data;


@Data
public class PagamentoDTO {	
	
	@Valid
	private DadosTransacaoDTO transacao;

}
