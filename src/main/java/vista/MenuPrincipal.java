package vista;

import controlador.Controlador;
import vista.administrador.MenuAdministrador;
import logica.CalculoRutaMasCorta;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Random;

public class MenuPrincipal extends JFrame {


    private final VentanaDetalleVuelo ventana = new VentanaDetalleVuelo();

    private final JPanel contentPane;
    public String VueloTxtOrigen = " ";
    public String VueloTxtDestino = " ";

    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JComboBox comboBoxDest = new JComboBox();
        comboBoxDest.setModel(new DefaultComboBoxModel(new String[]{"Mar del Plata,MDQ", "Buenos Aires,AEP", "Usuahia,USH", "Rio Gallegos,RGL", "Puerto Iguazu,IGR", "El Calafate,FTE", "Resistencia,RES", "Posada,PSS", "Formosa,FMA", "Rio Grande,RGA", "Trelew,REL", "Buenos Aires,EZE", "Rosario,ROS", "Mendoza,MDZ", "Cordoba,COR", "Salta,SLA", "Bariloche,BRC", "Neuquen,NQN", "El Calafate,FTE", "Tucuman,TUC", "Santiago Del Estero,SDE", "La Rioja,IRJ", "Comodoro Rivadavia,CRD", "San Martin Andes,CPC", "Bahia Blanca,BHI", "San Juan,AUQ", "San Rafael,AFA", "San Luis,LUQ", "Rio Gallegos,RGL"}));
        comboBoxDest.setBounds(237, 111, 176, 20);
        contentPane.add(comboBoxDest);

        JLabel lblNewLabel = new JLabel("Origen");
        lblNewLabel.setBounds(79, 87, 46, 14);
        contentPane.add(lblNewLabel);
        ventana.setVisible(false);

        JLabel lblNewLabel_1 = new JLabel("Destino");
        lblNewLabel_1.setBounds(280, 87, 46, 14);
        contentPane.add(lblNewLabel_1);
        JLabel LabelError = new JLabel("El Destino no puede ser el mismo que el Origen ");
        LabelError.setVisible(false);
        LabelError.setForeground(Color.RED);

        LabelError.setBounds(44, 159, 384, 16);
        contentPane.add(LabelError);

        JComboBox Tipo = new JComboBox();
        contentPane.add(Tipo);
        Tipo.setEnabled(true);
        JComboBox comboBoxOrg = new JComboBox();
        comboBoxOrg.setBounds(25, 111, 176, 20);
        contentPane.add(comboBoxOrg);

        comboBoxOrg.setModel(new DefaultComboBoxModel(new String[]{"Mar del Plata,MDQ", "Buenos Aires,AEP", "Usuahia,USH", "Rio Gallegos,RGL", "Puerto Iguazu,IGR", "El Calafate,FTE", "Resistencia,RES", "Posada,PSS", "Formosa,FMA", "Rio Grande,RGA", "Trelew,REL", "Buenos Aires,EZE", "Rosario,ROS", "Mendoza,MDZ", "Cordoba,COR", "Salta,SLA", "Bariloche,BRC", "Neuquen,NQN", "El Calafate,FTE", "Tucuman,TUC", "Santiago Del Estero,SDE", "La Rioja,IRJ", "Comodoro Rivadavia,CRD", "San Martin Andes,CPC", "Bahia Blanca,BHI", "San Juan,AUQ", "San Rafael,AFA", "San Luis,LUQ", "Rio Gallegos,RGL"}));

        Tipo.setModel(new DefaultComboBoxModel(new String[]{"Duracion", "Precio", "Escalas"}));
        Tipo.setBounds(54, 44, 89, 20);
        JButton btnNewButton_1 = new JButton("Salir");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // System.out.println(g);

                System.exit(0);
            }
        });
        btnNewButton_1.setBounds(68, 211, 89, 23);
        contentPane.add(btnNewButton_1);

        JLabel Etiqueta_filtrado = new JLabel("Filtrar Por");
        Etiqueta_filtrado.setBounds(44, 16, 99, 16);
        contentPane.add(Etiqueta_filtrado);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(269, 211, 89, 23);
        contentPane.add(btnBuscar);
        JButton btnAdmin = new JButton("Menu administrador");
        btnAdmin.setBounds(269, 30, 150, 23);
        contentPane.add(btnAdmin);
        btnBuscar.addActionListener(e -> {
            String selecOr = comboBoxOrg.getSelectedItem().toString();
            String selecDe = comboBoxDest.getSelectedItem().toString();
            String[] separador = selecOr.split(",", 0);
            String vueloOrigen = separador[1];
            separador = selecDe.split(",", 0);
            String vueloDestino = separador[1];

           VueloTxtOrigen = comboBoxOrg.getSelectedItem().toString();
            VueloTxtDestino = comboBoxDest.getSelectedItem().toString();

         //   VueloTxtOrigen = "MQP";
         //   VueloTxtDestino = "AEP";


            if (VueloTxtOrigen.equals(VueloTxtDestino)) {
                LabelError.setVisible(true);
            } else {
                LabelError.setVisible(false);
                ventana.setVisible(true);
                ventana.setSize(650, 350);
                try {
                    switch (Objects.requireNonNull(Tipo.getSelectedItem()).toString()) {
                        case "Precio" -> {
                           // CalculoRutaMasCorta calculoRutaMasCorta = new CalculoRutaMasCorta();
                         //   System.out.println( calculoRutaMasCorta.porPrecio("MDQ","AEP"));
                            ProcesoRecorridoPorPrecio procesoRecorridoPorPrecio = new ProcesoRecorridoPorPrecio(vueloOrigen, vueloDestino);
                            procesoRecorridoPorPrecio.run();
                            System.out.println(VueloTxtOrigen);

                        }
                        case "Duracion" -> {
                            CalculoRutaMasCorta calculoRutaMasCorta = new CalculoRutaMasCorta();
                          //  System.out.println( calculoRutaMasCorta.porDuracion("MDQ","AEP"));
                            ProcesoRecorridoPorDuracion procesoRecorridoPorDuracion = new ProcesoRecorridoPorDuracion(vueloOrigen, vueloDestino);

                            procesoRecorridoPorDuracion.run();




                        }

                    }


                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "No hay vuelos Disponibles", "Vuelo", JOptionPane.WARNING_MESSAGE);
                }


            }

        });
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                MenuAdministrador menuAdministrador = new MenuAdministrador();
                menuAdministrador.setVisible(true);

            }
        });

    }

    private class ProcesoRecorridoPorDuracion implements Runnable {

        public String vueloTxtOrigen = " ";
        public String vueloTxtDestino = " ";

        public ProcesoRecorridoPorDuracion(String vueloTxtOrigen, String vueloTxtDestino) {
            this.vueloTxtOrigen = vueloTxtOrigen;
            this.vueloTxtDestino = vueloTxtDestino;
        }
        Controlador controlador = new  Controlador();



        @Override
        public void run() {
            Random generador = new Random();
            // genera un numero aleatorio entre el 1000 y el 5000
           // int numero = 1000 + generador.nextInt(2000);


            String texto = "";
            try {

                //calculoRutaMasCorta.porDuracion(vueloTxtOrigen, vueloTxtDestino);
                //realiza un pausa de un tiempo expresado en milisegundos determiando
                Thread.sleep(1);

                ventana.SetTextEscribir(controlador.MostrarRutaPorDuracion(vueloTxtOrigen, vueloTxtDestino),vueloTxtOrigen,vueloTxtDestino);
                setVisible(true);
                //realiza un pausa de un tiempo expresado en milisegundos determiando
                Thread.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ProcesoRecorridoPorPrecio implements Runnable {

        public String vueloTxtOrigen = " ";
        public String vueloTxtDestino = " ";

        public ProcesoRecorridoPorPrecio(String vueloTxtOrigen, String vueloTxtDestino) {
            this.vueloTxtOrigen = vueloTxtOrigen;
            this.vueloTxtDestino = vueloTxtDestino;
        }

        CalculoRutaMasCorta calculoRutaMasCorta = new CalculoRutaMasCorta();


        @Override
        public void run() {
            Random generador = new Random();
            // genera un numero aleatorio entre el 1000 y el 5000
            int numero = 1000 + generador.nextInt(1000);
            Controlador controlador = new  Controlador();


            String texto = "";
            try {
                System.out.println(calculoRutaMasCorta.porPrecio(vueloTxtOrigen, vueloTxtDestino));
                //realiza un pausa de un tiempo expresado en milisegundos determiando
                Thread.sleep(numero);
                System.out.println("origen "+vueloTxtOrigen+"destino"+vueloTxtDestino);

                ventana.SetTextEscribir(controlador.MostraRutaPorPrecio(vueloTxtOrigen, vueloTxtDestino),vueloTxtOrigen,vueloTxtDestino);
                setVisible(true);
                //realiza un pausa de un tiempo expresado en milisegundos determiando
                Thread.sleep(numero);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

