package atvCofre;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("serial")
public class Real extends moedas {
	public Real(double valor) {

		this.valor = valor;
		Locale localeBR = new Locale("pt", "BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		this.valorDinheiro = dinheiro.format(valor); // instancia um novo local e formata o valor para sua determinada
														// moeda.
		this.currency = "Real";
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.date = df.format(d);// Cria nova data e a formata no padrão de indicado.
	}

	public double converter() { // retorna o valor convertido em reais.

		return valor * 1;
	}

	@Override
	public String toString() {
		return "Real- " + String.format("%.2f", valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
