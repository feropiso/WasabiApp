package com.netbiis.restaurantes.cliente;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.netbiis.restaurantes.Util;

public class FormCliente extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static int id_cliente;
	
	private JTextField nome;	
	private JTextField endereco;
	private JTextField telefone;
	private JTextField numeroCartao;
	
	private Cliente cliente;
		
	
	public FormCliente() {
		
		this(null);
	}


	public FormCliente(Cliente cliente) {
		
		super ();		
		
		this.setTitle("Cadastro de Cliente");
		
		if(cliente==null)
			this.cliente = new Cliente();
		else
			this.cliente = cliente;
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		this.setModal(true);
		id_cliente++;
		init();
	}
	
	public void init() {
		
		GridBagConstraints cons = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JLabel lb2 = new JLabel("Informe o nome do cliente:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
				
		nome = new JTextField(Util.vazioOuNull(cliente.getNome())?"":cliente.getNome());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(nome, cons);
		
		
		JLabel lb3 = new JLabel("Informe o endereço do cliente:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb3.setForeground(Color.white);
		panel.add(lb3, cons);
		
		
		endereco = new JTextField(Util.vazioOuNull(cliente.getEndereco())?"":cliente.getEndereco());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(endereco, cons);
		
		JLabel lb4 = new JLabel("Informe o telefone do cliente:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb4.setForeground(Color.white);
		panel.add(lb4, cons);
				
		telefone = new JTextField(Util.vazioOuNull(cliente.getTelefone())?"":cliente.getTelefone());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(telefone, cons);
		
		JLabel lb5 = new JLabel("Informe o número do cartão de crédito:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb5.setForeground(Color.white);
		panel.add(lb5, cons);
				
		numeroCartao = new JTextField(Util.vazioOuNull(cliente.getNumero_cartao())?"":cliente.getNumero_cartao());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(numeroCartao, cons);
		
		JButton bt_salvar = new JButton("Salvar");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.CENTER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(bt_salvar, cons);
				
		bt_salvar.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent ev) {							
					toSave();						
				}
			}				
		);
			
		setVisible(true);
		
	}


	private void toSave() {
		
		cliente.setIdCliente(id_cliente);
		cliente.setNome(this.nome.getText());
		cliente.setEndereco(this.endereco.getText());
		cliente.setTelefone(this.telefone.getText());
		cliente.setNumero_cartao(this.numeroCartao.getText());
		
		if (cliente == null) {
			
			Util.mostrarMsgErro("Digite nome do cliente.");
			return;
		}
		
		if (Util.vazioOuNull(cliente.getNome())) {
			
			Util.mostrarMsgErro("Digite nome do cliente.");
			return;
		}
		
		if (Util.vazioOuNull(cliente.getEndereco())) {
			
			Util.mostrarMsgErro("Digite o endereço do cliente.");
			return;
		}
		
		if (Util.vazioOuNull(cliente.getTelefone())) {
			
			Util.mostrarMsgErro("Digite o telefone do cliente.");
			return;
		}
		
		if (Util.vazioOuNull(cliente.getNumero_cartao())) {
			
			Util.mostrarMsgErro("Digite o número do cartão de crédito do cliente.");
			return;
		}

		Util.mostrarMsgSucesso();
		dispose();
		
	}
	
	public Cliente clienteCadastrado() {
		
		return cliente;
	}

	

}
