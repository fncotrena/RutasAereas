package vista.administrador;

import vista.VentanaDetalleVuelo;
import vista.administrador.adminCrud.AerepuertoVista;
import vista.administrador.adminCrud.VueloVista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdministrador extends JFrame{

    private final VentanaDetalleVuelo ventana = new VentanaDetalleVuelo();

    private final JPanel contentPane;
   public MenuAdministrador() {
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBounds(100, 100, 450, 300);
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);

       JButton btnNewButton_1 = new JButton("Salir");

       btnNewButton_1.setBounds(68, 211, 89, 23);
       contentPane.add(btnNewButton_1);


       JButton btnAeropuertos = new JButton("Aeropuertos");
       btnAeropuertos.setBounds(250, 100, 150, 23);
       contentPane.add(btnAeropuertos);

       JButton btnVuelos = new JButton("Vuelos");
       btnVuelos.setBounds(90, 100, 150, 23);
       contentPane.add(btnVuelos);

       btnVuelos.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               VueloVista objetoVista = new VueloVista();
               objetoVista.setVisible(true);
           }
       });

       btnAeropuertos.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               AerepuertoVista objetoVista = new AerepuertoVista();
               objetoVista.setVisible(true);
           }
       });
   }
}


