package com.netbiis.restaurantes;

import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class Util {
	
	public static BigDecimal converteParaBigDecimal(String s) {
		
		return new BigDecimal(s);		
	}
	
	public static void mostrarMsgSucesso() {
		
		JOptionPane.showMessageDialog(null, "Operação Realizada Com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mostrarMsgErro(String mensagem) {
		
		JOptionPane.showMessageDialog(null, mensagem, "Um erro Ocorreu", JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean vazioOuNull(String valor) {
		
		return valor == null || valor.length() == 0;
	}

}
