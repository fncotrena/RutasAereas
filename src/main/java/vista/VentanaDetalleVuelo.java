package vista;


import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.GraphPath;

import javax.swing.JFrame;


import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.JTextField;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.time.Duration;
import java.time.LocalTime;

public class VentanaDetalleVuelo extends JFrame {
	private JTextField textFprecio;
	private TextArea textfdetalles;
	private JTextField textFduracion;
	private JTextField textFcant;
	private JTextField textFtiempo;
	private JTextField textForigen;
	private JTextField textFdestino;

	public VentanaDetalleVuelo() {
		getContentPane().setLayout(null);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPrecio.setBounds(280, 74, 46, 14);
		getContentPane().add(lblPrecio);

		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDuracion.setBounds(474, 74, 68, 14);
		getContentPane().add(lblDuracion);

		JButton btnMostrar = new JButton("Detalles");
		btnMostrar.setBounds(276, 146, 89, 23);
		getContentPane().add(btnMostrar);

		textFprecio = new JTextField();
		textFprecio.setEditable(false);
		textFprecio.setEnabled(true);
		textFprecio.setBounds(267, 99, 89, 20);
		getContentPane().add(textFprecio);
		textFprecio.setColumns(10);
		textfdetalles = new TextArea();
		textfdetalles.setEditable(false);
		textfdetalles.setBounds(10, 175, 610, 136);
		getContentPane().add(textfdetalles);
		textfdetalles.setColumns(10);
		textfdetalles.setVisible(false);
		textFprecio.setEnabled(true);
		textFprecio.setText("");
		textFduracion = new JTextField();
		textFduracion.setText("");
		textFduracion.setEnabled(true);
		textFduracion.setEditable(false);
		textFduracion.setColumns(10);
		textFduracion.setBounds(474, 99, 68, 20);
		getContentPane().add(textFduracion);

		JLabel Lbtiempo = new JLabel("Salida-Llegada");
		Lbtiempo.setFont(new Font("Arial", Font.PLAIN, 16));
		Lbtiempo.setBounds(357, 74, 107, 14);
		getContentPane().add(Lbtiempo);

		JLabel lblEscalas = new JLabel("Escalas");
		lblEscalas.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEscalas.setBounds(552, 74, 68, 14);
		getContentPane().add(lblEscalas);

		textFcant = new JTextField();
		textFcant.setEnabled(true);
		textFcant.setEditable(false);
		// textFcant.setText("");
		textFcant.setColumns(10);
		textFcant.setBounds(552, 99, 68, 20);
		getContentPane().add(textFcant);

		textFtiempo = new JTextField();
		textFtiempo.setText("");
		textFtiempo.setEnabled(true);
		textFtiempo.setEditable(false);
		textFtiempo.setColumns(10);
		textFtiempo.setBounds(364, 99, 89, 20);
		getContentPane().add(textFtiempo);

		textForigen = new JTextField();
		textForigen.setText("");
		textForigen.setEnabled(true);
		textForigen.setEditable(false);
		textForigen.setColumns(10);
		textForigen.setBounds(10, 99, 112, 20);
		getContentPane().add(textForigen);

		textFdestino = new JTextField();
		textFdestino.setEnabled(true);
		textFdestino.setEditable(false);
		textFdestino.setColumns(10);
		textFdestino.setBounds(132, 99, 115, 20);
		getContentPane().add(textFdestino);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOrigen.setBounds(28, 74, 82, 14);
		getContentPane().add(lblOrigen);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDestino.setBounds(146, 74, 89, 14);
		getContentPane().add(lblDestino);

		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textfdetalles.setVisible(true);

			}
		});

	}
/*
	public void SetTextDetalles(List<Aeropuerto> datosAeropuertos, List<Vuelo> datosVuelos) {
		int duracion=0;
		
		duracion= datosVuelos.get(datosVuelos.size()-1).getLlegada() -datosVuelos.get(0).getSalida();
		textFprecio.setText("$" + Double.toString(datosVuelos.get(0).getPrecio()));	
		textFduracion.setText(LocalTime.MIN.plus(Duration.ofMinutes(duracion)).toString() +"hs");	
		textFtiempo.setText(LocalTime.MIN.plus(Duration.ofMinutes(datosVuelos.get(0).getSalida())).toString() + "-"
				+ LocalTime.MIN.plus(Duration.ofMinutes(datosVuelos.get(datosVuelos.size() - 1).getLlegada())).toString());
				
		textfdetalles.selectAll();
		textfdetalles.setText("");
		
		
		if (datosAeropuertos.size() == 2) {
			textFcant.setText("Directo");
		} else
			textFcant.setText(Integer.toString(datosAeropuertos.size() - 2));

		for (Aeropuerto a : datosAeropuertos) {
		//// for(Vuelo v: datosVuelos ) {
			textfdetalles.append(a.toString() + '\n');
	//	textfdetalles.append(v.toString() + '\n');}}
		}	

	}*/


	public void SetTextEscribir(GraphPath<Aeropuerto, Vuelo> grafo,String origen, String destino) {
		int duracion=0;
		textfdetalles.selectAll();
		textfdetalles.setText("");
		textFcant.setText(Integer.toString(grafo.getLength()));

		textForigen.setText(origen);
		textFdestino.setText(destino);
		for (Vuelo vuelo  :grafo.getEdgeList()){
			textfdetalles.append(vuelo.toString() + '\n');
			textFprecio.setText("$" + Double.toString(vuelo.getPrecio()));
             duracion+=vuelo.getHoraLlegada()-vuelo.getHoraSalida();
			textFtiempo.setText(LocalTime.MIN.plus(Duration.ofMinutes(vuelo.getHoraSalida())).toString() + "-"
					+ LocalTime.MIN.plus(Duration.ofMinutes(vuelo.getHoraLlegada())).toString());
		}

		textFduracion.setText(LocalTime.MIN.plus(Duration.ofMinutes(duracion)).toString() +"hs");

	}
}
