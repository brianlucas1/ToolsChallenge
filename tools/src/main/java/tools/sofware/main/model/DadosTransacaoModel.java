package tools.sofware.main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import tools.sofware.main.dto.DescricaoDTO;
import tools.sofware.main.dto.FormaPagamentoDTO;


@Data
public class DadosTransacaoModel {
	
	private TransacaoModel transacao;

	

}
