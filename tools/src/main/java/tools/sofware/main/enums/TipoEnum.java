package tools.sofware.main.enums;

import javax.validation.constraints.NotEmpty;

import lombok.Data;


public enum TipoEnum {
	
	PARCELADOLOJA("PARCELADO LOJA"),
	PARCELADOEMISSOR("PARCELADO EMISSOR"),
	AVISTA("A VISTA");
	
	@NotEmpty
	public String formaPagamento;
	
	TipoEnum(String forma){
		formaPagamento = forma;
	}
	
	
}
