package aplicacion;

import vista.MenuPrincipal;

import java.awt.EventQueue;




public class Aplicacion {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}			}
		});
	}}

	

	