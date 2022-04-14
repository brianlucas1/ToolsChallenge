package tools.sofware.main.dto;

import javax.persistence.Entity;


import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data

public class DescricaoDTO {
	
	@NotEmpty(message = "{campo.valor}")
	private String valor;
	@NotEmpty(message = "{campo.data}")
	private String dataHora;
	@NotEmpty(message = "{campo.estabelecimento}")
	private String estabelecimento;


}
