package tools.sofware.main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;



@Data
public class DescricaoModel {

	private double valor;

	private String dataHora;
	
	private String estabelecimento;
	
	private String nsu;
	
	private String codigoAutorizacao;
	
	private String status;
	
}
