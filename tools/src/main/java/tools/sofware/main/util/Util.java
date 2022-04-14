package tools.sofware.main.util;

import java.util.Random;

public class Util {

	public String intToString(int num) {
		
		String palavra =Integer.toString(num);
		
		return palavra;
	}

	public double stringToDouble(String palavra) {
		
		 double valor = Double.parseDouble(palavra);
		
		return valor;
	}

	public String geraCodigoAutorizacao() {	
		
		Random aleatorio = new Random();		
		int num  = aleatorio.nextInt() * 100;
		
		String auth = intToString(num);

		return auth;
	}

}
