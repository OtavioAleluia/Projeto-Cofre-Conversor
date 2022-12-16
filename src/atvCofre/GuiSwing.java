package atvCofre;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GuiSwing {

	private JFrame frame;
	private final ButtonGroup selectedCurrency = new ButtonGroup();
	private JTextField ValorInputAdd;
	private JTable table;
	private JTextField ValorInputSub;
	private final ButtonGroup removeSelectedCurrency = new ButtonGroup();
	Cofrinho cofrinho = new Cofrinho();
	DefaultTableModel dtm;
	DefaultTableModel dtm2;
	ArrayList<moedas> historico;
	int row;
	String header[] = new String[] { "Tipo", "Valor", "data de deposito" };
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiSwing window = new GuiSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void displayCoinsDetails() { // lista as moedas adicionadas recentemente.
		dtm.setRowCount(0);
		for (int i = 0; i < historico.size(); i++) {
			Object[] obj = { historico.get(i).currency, historico.get(i).valorDinheiro, historico.get(i).date };
			dtm.addRow(obj);
		}
	}

	public void listCoinsDetails() { // lista todas as moedas no cofrinho.
		dtm2.setRowCount(0);
		int listSize = cofrinho.listaMoedas.size();
		for (int i = listSize - 1; i >= 0; i--) {
			Object[] obj = { cofrinho.listaMoedas.get(i).currency, cofrinho.listaMoedas.get(i).valorDinheiro,
					cofrinho.listaMoedas.get(i).date };
			dtm2.addRow(obj);
		}
	}

	/**
	 * Create the application.
	 */
	public GuiSwing() {
		initialize();
		historico = new ArrayList<>();

		dtm = new DefaultTableModel(header, 0); // cria table de histórico
		table.setModel(dtm);

		dtm2 = new DefaultTableModel(header, 0); // cria table com todas as moedas do cofre.
		table_1.setModel(dtm2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cofrinho.Read();

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 713, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 186, 412);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() { // Chama a função save ao fechar o aplicativo.
			public void run() {
				cofrinho.save();
			}
		}));

		JLabel lblNewLabel = new JLabel("COFRINHO CONVERSOR");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 166, 13);
		panel.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(181, -26, 518, 438);
		frame.getContentPane().add(tabbedPane);

		JButton AddBtn = new JButton("GERENCIAR");
		AddBtn.setFocusPainted(false);
		AddBtn.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		AddBtn.setBackground(new Color(21, 25, 28));
		AddBtn.setForeground(new Color(255, 255, 255));
		AddBtn.setBorderPainted(false);
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		AddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				AddBtn.setBackground(new Color(55, 66, 74));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				AddBtn.setBackground(new Color(21, 25, 28));
			}
		});
		AddBtn.setBounds(0, 67, 186, 35);
		panel.add(AddBtn);

		JButton ListarBtn = new JButton("LISTAR");
		ListarBtn.setFocusPainted(false);
		ListarBtn.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		ListarBtn.setForeground(new Color(255, 255, 255));
		ListarBtn.setBackground(new Color(21, 25, 28));
		ListarBtn.setBorderPainted(false);
		ListarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);

				listCoinsDetails();
			}
		});
		ListarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ListarBtn.setBackground(new Color(55, 66, 74));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				ListarBtn.setBackground(new Color(21, 25, 28));
			}
		});
		ListarBtn.setBounds(0, 125, 186, 35);
		panel.add(ListarBtn);

		JButton ConvertBtn = new JButton("CONVERTER");
		ConvertBtn.setFocusPainted(false);
		ConvertBtn.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		ConvertBtn.setBackground(new Color(21, 25, 28));
		ConvertBtn.setForeground(new Color(255, 255, 255));
		ConvertBtn.setBorderPainted(false);
		ConvertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		ConvertBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ConvertBtn.setBackground(new Color(55, 66, 74));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				ConvertBtn.setBackground(new Color(21, 25, 28));
			}
		});
		ConvertBtn.setBounds(0, 188, 186, 35);
		panel.add(ConvertBtn);

		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(new Color(34, 40, 44));
		tabbedPane.addTab("New tab", null, MainPanel, null);
		MainPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("SEU COFRINHO");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblNewLabel_1.setBounds(98, 10, 293, 29);
		MainPanel.add(lblNewLabel_1);

		JRadioButton DolarBtn = new JRadioButton("DOLAR");
		DolarBtn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		DolarBtn.setFocusPainted(false);
		DolarBtn.setBackground(new Color(34, 40, 44));
		DolarBtn.setForeground(new Color(255, 255, 255));
		selectedCurrency.add(DolarBtn);
		DolarBtn.setBounds(199, 49, 72, 21);
		MainPanel.add(DolarBtn);

		JRadioButton EuroBtn = new JRadioButton("EURO");
		EuroBtn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		EuroBtn.setFocusPainted(false);
		EuroBtn.setBackground(new Color(34, 40, 44));
		EuroBtn.setForeground(new Color(255, 255, 255));
		selectedCurrency.add(EuroBtn);
		EuroBtn.setBounds(284, 49, 65, 21);
		MainPanel.add(EuroBtn);

		JRadioButton RealBtn = new JRadioButton("REAL");
		RealBtn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		RealBtn.setFocusPainted(false);
		RealBtn.setBackground(new Color(34, 40, 44));
		RealBtn.setForeground(new Color(255, 255, 255));
		selectedCurrency.add(RealBtn);
		RealBtn.setBounds(351, 49, 65, 21);
		MainPanel.add(RealBtn);

		JLabel lblNewLabel_5 = new JLabel("Digite o valor da moeda:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		lblNewLabel_5.setBounds(10, 68, 183, 29);
		MainPanel.add(lblNewLabel_5);

		ValorInputAdd = new JTextField();
		ValorInputAdd.setBorder(null);
		ValorInputAdd.setCaretColor(new Color(255, 255, 255));
		ValorInputAdd.setFont(new Font("Tahoma", Font.BOLD, 10));
		ValorInputAdd.setForeground(new Color(255, 255, 255));
		ValorInputAdd.setBackground(new Color(55, 66, 74));
		ValorInputAdd.setBounds(203, 76, 96, 19);
		MainPanel.add(ValorInputAdd);
		ValorInputAdd.setColumns(10);

		JButton btnNewButton_3 = new JButton("ADICIONAR");
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setFocusTraversalKeysEnabled(false);
		btnNewButton_3.setBackground(new Color(21, 25, 28));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		AddBtn.setBorderPainted(false);
		btnNewButton_3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNewButton_3.setBackground(new Color(55, 66, 74));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNewButton_3.setBackground(new Color(21, 25, 28));
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DolarBtn.setActionCommand("Dolar");
				EuroBtn.setActionCommand("Euro");
				RealBtn.setActionCommand("Real");
				String selecionadoADD = "";
				try {
					selecionadoADD = selectedCurrency.getSelection().getActionCommand();

				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "ERRO! Selecione um tipo de Moeda para adiconar!"); // checa se
																											// o tipo de
																											// moeda foi
																											// selecionado.
				}
				moedas moeda = null;
				if (selecionadoADD == "Dolar") {
					try {
						Double valor = Double.parseDouble(ValorInputAdd.getText().replace(",", "."));
						moeda = new Dolar(valor);
						cofrinho.add(moeda);
						historico.add(moeda);
						JOptionPane.showMessageDialog(null, "Moeda Adicionada ao seu cofrinho!");
						displayCoinsDetails();

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "ERRO! Digite um Valor Válido. Ex: 15,25"); // checa se o
																										// valor
																										// inserido é
																										// valido.
					}
				} else if (selecionadoADD == "Euro") {
					try {
						Double valor = Double.parseDouble(ValorInputAdd.getText().replace(",", "."));
						moeda = new Euro(valor);
						cofrinho.add(moeda);
						historico.add(moeda);
						JOptionPane.showMessageDialog(null, "Moeda Adicionada ao seu cofrinho!");
						displayCoinsDetails();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "ERRO! Digite um Valor Válido. Ex: 15,25");
					}
				} else if (selecionadoADD == "Real") {
					try {
						Double valor = Double.parseDouble(ValorInputAdd.getText().replace(",", "."));
						moeda = new Real(valor);
						cofrinho.add(moeda);
						historico.add(moeda);
						JOptionPane.showMessageDialog(null, "Moeda Adicionada ao seu cofrinho!");
						displayCoinsDetails();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "ERRO! Digite um Valor Válido. Ex: 15,25");
					}
				}

			}

		});
		btnNewButton_3.setBounds(349, 76, 103, 21);
		MainPanel.add(btnNewButton_3);

		JLabel lblNewLabel_2 = new JLabel("Adicione uma moeda :");
		lblNewLabel_2.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(10, 53, 180, 13);
		MainPanel.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(55, 66, 74));
		scrollPane.setBounds(10, 100, 493, 172);
		scrollPane.getViewport().setBackground(new Color(55, 66, 74));
		MainPanel.add(scrollPane);

		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setBorder(null);
		table.setOpaque(false);
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(89, 106, 119));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setGridColor(new Color(103, 122, 137));
		table.setBorder(null);
		table.setBackground(new Color(55, 66, 74));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_7 = new JLabel("Remova uma moeda:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		lblNewLabel_7.setBounds(13, 296, 180, 21);
		MainPanel.add(lblNewLabel_7);

		JRadioButton RmvDolar = new JRadioButton("DOLAR");
		RmvDolar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		RmvDolar.setBackground(new Color(30, 40, 44));
		RmvDolar.setFocusPainted(false);
		RmvDolar.setForeground(new Color(255, 255, 255));
		removeSelectedCurrency.add(RmvDolar);
		RmvDolar.setBounds(185, 299, 65, 21);
		MainPanel.add(RmvDolar);

		JRadioButton RmvEuro = new JRadioButton("EURO");
		RmvEuro.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		RmvEuro.setBackground(new Color(30, 40, 44));
		RmvEuro.setFocusPainted(false);
		RmvEuro.setForeground(new Color(255, 255, 255));
		removeSelectedCurrency.add(RmvEuro);
		RmvEuro.setBounds(277, 299, 72, 21);
		MainPanel.add(RmvEuro);

		JRadioButton RmvReal = new JRadioButton("REAL");
		RmvReal.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		RmvReal.setBackground(new Color(30, 40, 44));
		RmvReal.setFocusPainted(false);
		RmvReal.setForeground(new Color(255, 255, 255));
		removeSelectedCurrency.add(RmvReal);
		RmvReal.setBounds(366, 299, 65, 21);
		MainPanel.add(RmvReal);

		JLabel lblNewLabel_8 = new JLabel("Digite o valor da moeda:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		lblNewLabel_8.setBounds(10, 332, 216, 27);
		MainPanel.add(lblNewLabel_8);

		ValorInputSub = new JTextField();
		ValorInputSub.setFont(new Font("Tahoma", Font.BOLD, 10));
		ValorInputSub.setForeground(new Color(255, 255, 255));
		ValorInputSub.setBorder(null);
		ValorInputSub.setCaretColor(new Color(255, 255, 255));
		ValorInputSub.setBackground(new Color(55, 66, 74));
		ValorInputSub.setBounds(203, 339, 96, 19);
		MainPanel.add(ValorInputSub);
		ValorInputSub.setColumns(10);

		JButton btnNewButton_1 = new JButton("REMOVER");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setBackground(new Color(21, 25, 28));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		AddBtn.setBorderPainted(false);
		btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNewButton_1.setBackground(new Color(55, 66, 74));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNewButton_1.setBackground(new Color(21, 25, 28));
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RmvDolar.setActionCommand("Dolar");
				RmvEuro.setActionCommand("Euro");
				RmvReal.setActionCommand("Real");
				String selecionadoRMV = "";
				double valor = 0;
				String input = ValorInputSub.getText();

				try {
					selecionadoRMV = removeSelectedCurrency.getSelection().getActionCommand();

				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "ERRO! Selecione um tipo de Moeda para remover!");
				}

				moedas moeda = null;

				if (selecionadoRMV == "Dolar") {
					// Verifica se o input é válido.
					try {
						valor = Double.parseDouble(input.replace(",", "."));
						moeda = new Dolar(valor);
						cofrinho.sub(moeda);
						historico.remove(moeda);
						displayCoinsDetails();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "ERRO! Digite um Valor Válido. Ex: 15,25");
					}
					// continua com a criação do objeto para a remoção.

				} else if (selecionadoRMV == "Euro") {
					try {
						valor = Double.parseDouble(ValorInputSub.getText().replace(",", "."));
						moeda = new Euro(valor);
						cofrinho.sub(moeda);
						historico.remove(moeda);
						displayCoinsDetails();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "ERRO! Digite um Valor Válido. Ex: 15,25");
					}

				} else if (selecionadoRMV == "Real") {
					try {
						valor = Double.parseDouble(ValorInputSub.getText().replace(",", "."));
						moeda = new Real(valor);
						cofrinho.sub(moeda);
						historico.remove(moeda);
						displayCoinsDetails();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "ERRO! Digite um Valor Válido. Ex: 15,25");
					}

				}
			}

		});
		btnNewButton_1.setBounds(349, 338, 103, 21);
		MainPanel.add(btnNewButton_1);

		JPanel ListPanel = new JPanel();
		ListPanel.setBackground(new Color(30, 40, 44));
		tabbedPane.addTab("New tab", null, ListPanel, null);
		ListPanel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("LISTA DE MOEDAS");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblNewLabel_3.setBounds(0, 5, 513, 25);
		ListPanel.add(lblNewLabel_3);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(new Color(30, 40, 44));
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBackground(new Color(30, 40, 44));
		scrollPane_1.setBounds(21, 60, 482, 222);
		ListPanel.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setForeground(new Color(255, 255, 255));
		table_1.setBorder(null);
		table_1.setOpaque(false);
		table_1.getTableHeader().setOpaque(false);
		table_1.getTableHeader().setBackground(new Color(89, 106, 119));
		table_1.getTableHeader().setForeground(new Color(255, 255, 255));
		table_1.setShowVerticalLines(false);
		table_1.setGridColor(new Color(103, 122, 137));
		table_1.setBorder(null);
		table_1.setBackground(new Color(55, 66, 74));
		scrollPane_1.setViewportView(table_1);

		JPanel Convert = new JPanel();
		Convert.setBackground(new Color(30, 40, 44));
		tabbedPane.addTab("New tab", null, Convert, null);
		Convert.setLayout(null);

		JLabel lblNewLabel_9 = new JLabel("CONVERTER");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(0, 0, 513, 38);
		Convert.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("exibe o valor total das moedas para real");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setForeground(new Color(255, 255, 255));
		lblNewLabel_10.setFont(new Font("Segoe UI Symbol", Font.ITALIC, 13));
		lblNewLabel_10.setBounds(10, 48, 503, 22);
		Convert.add(lblNewLabel_10);

		JLabel exibValorConvert = new JLabel("VALOR CONVERTIDO");
		exibValorConvert.setHorizontalAlignment(SwingConstants.CENTER);
		exibValorConvert.setForeground(new Color(255, 255, 255));
		exibValorConvert.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		exibValorConvert.setBounds(10, 92, 493, 26);
		Convert.add(exibValorConvert);

		JButton convertBtn = new JButton("CONVERTER TOTAL");
		convertBtn.setBorderPainted(false);
		convertBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		convertBtn.setFocusable(false);
		convertBtn.setFocusPainted(false);
		convertBtn.setFocusTraversalKeysEnabled(false);
		convertBtn.setBackground(new Color(21, 25, 28));
		convertBtn.setForeground(new Color(255, 255, 255));
		AddBtn.setBorderPainted(false);
		convertBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				convertBtn.setBackground(new Color(55, 66, 74));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				convertBtn.setBackground(new Color(21, 25, 28));
			}
		});
		convertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale localeBR = new Locale("pt", "BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

				exibValorConvert.setText(dinheiro.format(cofrinho.total()));

			}
		});
		convertBtn.setBounds(180, 134, 154, 33);
		Convert.add(convertBtn);
		AddBtn.setBorderPainted(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(new Color(103, 122, 137));
		panel_1.setBounds(0, 177, 513, 234);
		Convert.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_12 = new JLabel("QUEBRAR COFRINHO");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblNewLabel_12.setForeground(new Color(255, 255, 255));
		lblNewLabel_12.setBounds(0, 0, 513, 35);
		panel_1.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Quebra o cofrinho. deletando todas as moedas na lista.");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setBounds(0, 75, 513, 18);
		panel_1.add(lblNewLabel_13);
		lblNewLabel_13.setForeground(new Color(255, 255, 255));
		lblNewLabel_13.setFont(new Font("Segoe UI Symbol", Font.ITALIC, 13));

		JButton btnNewButton_4 = new JButton("QUEBRAR COFRINHO");
		btnNewButton_4.setBounds(181, 158, 154, 33);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_4.setFocusable(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setFocusTraversalKeysEnabled(false);
		btnNewButton_4.setBackground(new Color(65, 78, 86));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNewButton_4.setBackground(new Color(21, 25, 28));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNewButton_4.setBackground(new Color(65, 78, 86));
			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Todas as moedas serão apagadas, deseja continuar?",
						"AVISO!", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					cofrinho.Quebrar();
					historico.removeAll(historico);
					listCoinsDetails();
					displayCoinsDetails();
					Locale localeBR = new Locale("pt", "BR");
					NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
					exibValorConvert.setText(dinheiro.format(cofrinho.total()));
					JOptionPane.showMessageDialog(null, "Cofrinho Quebrado! todas as moedas foram deletadas.");
				}

			}
		});
	}
}
