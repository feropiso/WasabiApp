package com.netbiis.restaurantes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.netbiis.restaurantes.cliente.Cliente;
import com.netbiis.restaurantes.cliente.FormCliente;
import com.netbiis.restaurantes.pedido.FormPedido;
import com.netbiis.restaurantes.pedido.Pedido;
import com.netbiis.restaurantes.produto.Produto;

public class WasabiApp extends JFrame {
	
	private JTable resultadoJTable;		
	
	private List<Cliente> listaClientes;
	private List<Produto> listaProdutos;
	private List<Pedido> listaPedidos;
	
	public WasabiApp() {
		
		super ("Wassabi Restaurante");			
		setLayout(new GridBagLayout());	
		
		this.listaClientes = new ArrayList<Cliente>();
		this.listaProdutos = new ArrayList<Produto>();
		this.listaPedidos = new ArrayList<Pedido>();
				
		cardapio();
		iniciar();		
	}
	
	public void iniciar() {
		
		GridBagConstraints cons = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		JPanel panelbts = new JPanel(new GridBagLayout());
		panelbts.setBackground(Color.black);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(0,0,0,0);
		panel.add(panelbts, cons);
		
		
		JPanel panelPesquisa = new JPanel(new GridBagLayout());
		panelPesquisa.setBackground(Color.black);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(0,0,0,0);
		panel.add(panelPesquisa, cons);
				
		JButton cardapioJButton = new JButton("Cardapio");		
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelbts.add(cardapioJButton, cons);
		
		
		JButton pedidoJButton = new JButton("Pedido");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.EAST;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelbts.add(pedidoJButton, cons);
		
		resultadoJTable = new JTable();
		resultadoJTable.setModel(new DefaultTableModel(
	            new Object [][] {},
	            new String [] {"Prato", "Valor R$", "Categoria"}) {
	           
			private static final long serialVersionUID = 1L;
				
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	               return false;
	        }
	    });
		
		
		resultadoJTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		resultadoJTable.setSurrendersFocusOnKeystroke(true);
		
		
		JScrollPane barraRolagem = new JScrollPane(resultadoJTable);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(5,5,5,5);
		panel.add(barraRolagem, cons);
		
		JPanel panelRodape = new JPanel(new GridBagLayout());
		panelRodape.setBackground(Color.black);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(0,0,0,0);
		panel.add(panelRodape, cons);
				
		cardapioJButton.addActionListener(
				new ActionListener() {
					
					public void actionPerformed(ActionEvent ev) {
						
						mostraCardapio();
					
					}					
				}				
		);
		
		pedidoJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						realizaPedido();										
					}					
				}				
		);
		
		
	}
		
	private void mostraCardapio() {
		
		this.limpaTabela();
		
		
		DefaultTableModel model = (DefaultTableModel) this.resultadoJTable.getModel();
		
		for (Produto produto : this.listaProdutos)  
            model.addRow(new String[]{produto.getNomePrato()+"", produto.getPreco()+"", produto.getCategoria()+""});
	}

	private void realizaPedido()  {
				
		FormCliente form = new FormCliente();
		
		listaClientes.add(form.clienteCadastrado());
				
		if (listaClientes.isEmpty()) {
			Util.mostrarMsgErro("É preciso realizar o cadastro do cliente.");
			return;
		}
		
		FormPedido form2 = new FormPedido(listaProdutos);
			
		listaPedidos = form2.pedidoRealizado();
		
		finalizaPedido(form2);		
	}
	
	private void finalizaPedido(FormPedido form2) {
		
		FileWriter arq = null;
		
		try {
			arq = new FileWriter("Pedido.txt");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		PrintWriter gravaarq = new PrintWriter(arq);
		
		gravaarq.println("================================");
		gravaarq.println("Endereço de cobrança:");
		
		for (Cliente cliente: listaClientes) {			
			gravaarq.print(cliente.getNome()+" \t ");
			gravaarq.print(cliente.getTelefone()+" \t ");
			gravaarq.println(cliente.getEndereco());    
		}
		
		gravaarq.println("================================");
		gravaarq.println("Prato \t Quantidade \t Preço (R$)  ");
		
		for (Pedido pedido: listaPedidos) {			
			gravaarq.print(form2.retornaNomePrato(pedido.getChav_estr_produto())+" \t ");
			gravaarq.print(pedido.getQuantidade()+" \t ");
			gravaarq.println(pedido.getValorTotal());    
		}
		
		try {
			arq.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		gravaarq.close();
	}
	
	private void limpaTabela() {
		
		DefaultTableModel model = (DefaultTableModel) this.resultadoJTable.getModel();
		
		int num =  model.getRowCount();
		
		for(int i =0; i< num; i++)
			model.removeRow(0);
	}
	
	private void cardapio() {
				
		String[] opcao = { "Promocão", "Entradas", "Temaki Especial", "Holl Wassabi",  "Bebiba"};
		
		String[] prato = { "Combo Promo 1", "Combo Promo 2", "Combo Promo 3", "Promo Vegano", 
				"Salmão", "Lula em Anéis", "Camarão empanado (5 unidades)", "Guioza", 
				"Temaki Exotic", "Temaki Poró", "Temaki Salmão Especial", 
				"Temaki wassabi", "Wassabi holl met", "Holl tartar de salmão", "Kasai met", "Kasai",  
				"Água mineral 200 ml", "Coca Cola lata", "Suco Laranja", "Cerveja Long Neck"};
		
		String[] preco = { "40,90", "53,90", "49,90", "40,90",  "39,00", "41,00", "43,50", "32,00", 
				"41,50", "42,50", "43,50", "46,50", "23,50", "46,50", "23,50", "47,50", 
				"4,00", "8,50", "10,50", "7,55" };
		
		cadastraProduto(opcao, prato, preco);
		
	}
	
	private void cadastraProduto(String [] categoria, String[] prato, String [] preco) {
		
		int cont = 0;
		
		for (int i = 0; i < prato.length; i++) {
			
			Produto produto = new Produto();
			
			if (i % 4 == 0)
				cont++;
				
			produto.setCategoria(categoria[cont-1]);			
			
			produto.setIdProduto(i+1);
			produto.setNomePrato(prato[i]);
			produto.setPreco(preco[i]);
		
			listaProdutos.add(produto);			
			
		}
	}
	
	public static void main(String[] args) {
		
		WasabiApp wasabiApp = new WasabiApp();
		wasabiApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wasabiApp.setSize(500, 600);
		wasabiApp.setLocationRelativeTo(null);
		wasabiApp.setVisible(true);

	}

}
