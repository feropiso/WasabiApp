package com.netbiis.restaurantes.pedido;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.netbiis.restaurantes.Util;
import com.netbiis.restaurantes.produto.Produto;

public class FormPedido extends JDialog {
	
	private JLabel valorFinal;
	private JLabel valorUnitario;
	
	private JTable vendaJTable;
	
	private JTextField quantidadeJTF;
		
	private List<Produto> listaProdutos;
	private List<Pedido> lista_de_pedidos;
	
	private JComboBox<String> comboProdutos;
	
	private int quantidadeTotal;
	
	private String valor_Final;
	private String valorDesconto;
	private String precoBrutoTotal;	
	
	public FormPedido(List<Produto> lista_de_produtos) {
		
		super ();			
		this.setTitle("Pedido");
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550, 550);
		setLocationRelativeTo(null);
		this.setModal(true);
	
		inicializador(lista_de_produtos);
	}
	
	private void inicializador(List<Produto> lista_de_produtos) {
		
		valor_Final = "";		
		valorDesconto = "";
		precoBrutoTotal = "";
		quantidadeTotal = 0;
		
		this.listaProdutos = lista_de_produtos;
		this.lista_de_pedidos = new ArrayList<Pedido>();
				
		GridBagConstraints cons = new GridBagConstraints();
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JLabel lb1 = new JLabel("Selecione o prato:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(15,5,0,5);			
		lb1.setForeground(Color.white);
		panel.add(lb1, cons);
		
		
		comboProdutos = new JComboBox<String>();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panel.add(comboProdutos, cons);
		
		
		valorUnitario = new JLabel();
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.NORTHEAST;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		valorUnitario.setForeground(Color.white);
		panel.add(valorUnitario, cons);
		
		
		JLabel lb2 = new JLabel("Informe a Quantidade:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
		
		
		quantidadeJTF = new JTextField();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(quantidadeJTF, cons);
		
		
		JButton addJButton = new JButton("Adicionar");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.CENTER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(addJButton, cons);
		
		
		vendaJTable = new JTable();		
		vendaJTable.setModel(new DefaultTableModel(
	            new Object [][] {},
	            new String [] {"Nome", "Quantidade", "Preço R$"}) {
	           
			private static final long serialVersionUID = 1L;
				
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	        		        	
	               return false;
	        }
	    });
		
		
		vendaJTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		vendaJTable.setSurrendersFocusOnKeystroke(true);
		vendaJTable.getColumnModel().getColumn(0).setPreferredWidth(340);
		vendaJTable.getColumnModel().getColumn(1).setPreferredWidth(75);		
		vendaJTable.getColumnModel().getColumn(2).setPreferredWidth(65);
		
		
		JScrollPane barraRolagem = new JScrollPane(vendaJTable);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(5,5,5,5);
		panel.add(barraRolagem, cons);
				
		valorFinal = new JLabel("Total: R$ 0,00");
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.NORTHEAST;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		valorFinal.setForeground(Color.white);
		panel.add(valorFinal, cons);
		
		
		JButton bt_finalizar = new JButton("Finalizar Compra");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.CENTER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,15,5);		
		panel.add(bt_finalizar, cons);
		
		
		comboProdutos.addActionListener(
				new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				carregaValor();
					
			}}				
		);
		
		
		addJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {			
				
				addItem();				
			
			}}				
		);
		
				
		bt_finalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				finalizaCompra();
					
			}}				
		);


		carregaProdutos();
		setVisible(true);		
	} 
	
	private void finalizaCompra() {
				
		if (quantidadeTotal == 0) {
			Util.mostrarMsgErro("Adicione produto.");
			return;
		}
		
		Util.mostrarMsgSucesso();					
		dispose();		
	}
	
	public List<Pedido> pedidoRealizado() {
		
		return lista_de_pedidos;
	}

	private void addItem() {
		
		DefaultTableModel model = (DefaultTableModel) this.vendaJTable.getModel();
		
		
		if(validaDados()) {
			
			Pedido itenspedido = new Pedido();
			
			int quantidadeFornecida = validaQuantidadeFornecida();
		
			itenspedido.setQuantidade(quantidadeFornecida);
			
			Produto produtoSelecionado = this.listaProdutos.get(comboProdutos.getSelectedIndex()-1);
			
			itenspedido.setChav_estr_produto(produtoSelecionado.getIdProduto());
				
			String preco = produtoSelecionado.getPreco().replace(",", ".");				
			BigDecimal precobigdecimal = Util.converteParaBigDecimal(preco);							
			BigDecimal precoXQuant = precobigdecimal.multiply(Util.converteParaBigDecimal(""+quantidadeFornecida));
			
			itenspedido.setValorTotal(formataPreco(precoXQuant.toString()));
							
			lista_de_pedidos.add(itenspedido);
			
			this.limpaTabela();
			
			for(Pedido ip: lista_de_pedidos) 
				model.addRow(new String[]{retornaNomePrato(ip.getChav_estr_produto()), ip.getQuantidade()+"",
						ip.getValorTotal()});
			
			int quantidade_final = 0;
			double preco_final = 0.0;	
			
			for(int i = 0; i < lista_de_pedidos.size(); i++) {
					
				String valorRecuperado = lista_de_pedidos.get(i).getValorTotal().replace(",", ".");
				int quantidadeRecuperada = lista_de_pedidos.get(i).getQuantidade();
					
				BigDecimal preco_Corrente = Util.converteParaBigDecimal(valorRecuperado);
				Double valorauxiliar = preco_Corrente.doubleValue();
				preco_final += valorauxiliar;
				quantidade_final += quantidadeRecuperada;					
			}				
				
			quantidadeTotal = quantidade_final;
			precoBrutoTotal = formataPreco(""+preco_final);					
			valorFinal.setText("Total: R$ "+precoBrutoTotal);
			
			valorDesconto = "0,00";
			valor_Final = precoBrutoTotal;
			
		}	
		
		model.fireTableDataChanged();					
	}
		
	private String formataPreco(String s) {
		
		String aux = s;
		
		if(aux.contains(".")) {				
			aux = aux.replace(".", ",");				
			if(aux.indexOf(",") == aux.length()-1)
				aux += "00";
			else if(aux.indexOf(",") == aux.length()-2)
				aux += "0";
			else if(aux.length() - aux.indexOf(",")>3)
				aux = aux.substring(0, aux.indexOf(",")+3);
		}
		else
			aux += ",00";			
		
		return aux;
	}
	
	private boolean validaDados() {
		
		int index = comboProdutos.getSelectedIndex()-1;
		
		if(index < 0) {
			Util.mostrarMsgErro("Selecione um produto.");
			return false;
		}
		
		Produto produtoSelecionado = this.listaProdutos.get(index);
		
		int chav = 0;
		
		for(int i = 0; i < lista_de_pedidos.size(); i++) {
			
			chav = lista_de_pedidos.get(i).getChav_estr_produto();
			
			if (produtoSelecionado.getIdProduto() == chav) {
				Util.mostrarMsgErro("Produto já adicionado!");
				return false;
			}
		}
		
		
		return true;	
	}
	
	private void carregaValor() {
		
		valorUnitario.setText("");
		int index = comboProdutos.getSelectedIndex()-1;
		
		if (index >= 0) {
			Produto produtoSelecionado = this.listaProdutos.get(index);
			valorUnitario.setText("Preço: R$ "+produtoSelecionado.getPreco());						
		}
		
	}
	
	private void limpaTabela() {
		
		DefaultTableModel model = (DefaultTableModel) this.vendaJTable.getModel();
		
		int num =  model.getRowCount();
		
		for(int i =0; i< num; i++)
			model.removeRow(0);
	}
	
	private void carregaProdutos() {
		
		this.comboProdutos.removeAllItems();
		
		this.comboProdutos.addItem("...");
		
		if(this.listaProdutos != null || !this.listaProdutos.isEmpty()) {
			
			for (Produto produto : this.listaProdutos) 
				this.comboProdutos.addItem(produto.getNomePrato());			
		}		
	}
	
	private int validaQuantidadeFornecida() {
		
		if (Util.vazioOuNull(this.quantidadeJTF.getText())) 			
			return 1;
		
		else {
			
			int aux = 0;
		
			try {	
			
			aux = Integer.parseInt(this.quantidadeJTF.getText());
			
			if (aux <= 0) 
				Util.mostrarMsgErro("Quantidade fornecida é inválida.");				
			}		
			catch (InputMismatchException inputMismatchException) {
				
				Util.mostrarMsgErro("A quantidade é um número inteiro. Ex: 1,2,3...");
				inputMismatchException.printStackTrace();			
			}		
			return aux;
		}			
	}
	
	public String retornaNomePrato(int id) {
		
		int chav = 0;
		String prato = "";
		
		for(int i = 0; i < listaProdutos.size(); i++) {
			
			chav = listaProdutos.get(i).getIdProduto();
			
			if (id == chav) {
				prato = listaProdutos.get(i).getNomePrato();
				break;
			}
		}
		
		return prato;		
	}
	

}
