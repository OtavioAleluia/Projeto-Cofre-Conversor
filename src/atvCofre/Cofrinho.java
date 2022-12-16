package atvCofre;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cofrinho {

	ArrayList<moedas> listaMoedas = new ArrayList<>();

	public void add(moedas x) {
		listaMoedas.add(x);
	}

	public void sub(moedas x) {
		if (listaMoedas.remove(x)) {
			listaMoedas.remove(x);
			JOptionPane.showMessageDialog(null, "Moeda removida do seu cofrinho.");
		}
		else {
			JOptionPane.showMessageDialog(null, "Moeda não encontrada! Confira o valor e tente novamente.");
		}
	}

	
	
	
	
	public ArrayList<moedas> list() {

		return listaMoedas;
	}

	public double total() {
		double total = 0;
		for (moedas y : listaMoedas) {
			 total += y.converter();
		}
		return total;
		
	}
	
	public void save()  {
		try {
			FileOutputStream fos = new FileOutputStream("walletSave.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listaMoedas);
			oos.close();
			
		}catch (FileNotFoundException e) {
			
		}catch (IOException e ) {
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void Read () {
		try {
			FileInputStream fis = new FileInputStream("walletSave.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<moedas> loadedList = (ArrayList<moedas>) ois.readObject();
			this.listaMoedas = loadedList;
			ois.close();
			
		}catch ( ClassNotFoundException | IOException e ) {
				
		}
	}
		
	public void Quebrar () {
		this.listaMoedas.removeAll(listaMoedas);
	}
			
	}

