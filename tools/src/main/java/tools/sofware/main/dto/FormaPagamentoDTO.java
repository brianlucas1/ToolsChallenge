package tools.sofware.main.dto;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import tools.sofware.main.enums.TipoEnum;
import lombok.Data;


@Data

public class FormaPagamentoDTO {

	@Valid
	private TipoEnum tipo;
	@NotEmpty(message = "{campo.parcelas}")
	private String parcelas;
	
}
