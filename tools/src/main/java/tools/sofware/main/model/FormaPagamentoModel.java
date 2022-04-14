package tools.sofware.main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import tools.sofware.main.enums.TipoEnum;
import lombok.Data;



@Data
public class FormaPagamentoModel {
	

	private TipoEnum tipo;

	private String parcelas;

}
