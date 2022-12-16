package atvCofre;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class moedas implements Serializable {
	public String date;
	public String currency;
	public double valor;
	public String valorDinheiro;

	public abstract String toString();

	public double converter() {
		return 0;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		moedas other = (moedas) obj;
		return Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}

}
