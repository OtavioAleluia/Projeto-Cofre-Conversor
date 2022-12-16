package atvCofre;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("serial")
public class Euro extends moedas {
	public Euro(double valor) {
		Locale localeFR = new Locale("fr", "FR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeFR);
		this.valorDinheiro = dinheiro.format(valor); // instancia um novo local e formata o valor para sua determinada
		this.valor = valor;										// moeda.

		this.currency = "Euro";
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.date = df.format(d);// Cria nova data e a formata no padrão de indicado.

	}

	public double converter() { // retorna o valor convertido em reais.

		return valor * 7;
	}

	public String toString() {
		return "Euro- " + String.format("%.2f", valor);
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
