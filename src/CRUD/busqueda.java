package CRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class busqueda {
    private JLabel placa;
    private JTextField Cedula;
    public JButton buscar;
    public  JPanel mainPanel;
    private JButton volverButton;
    private JButton limpiarButton;

    public busqueda() {
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = Cedula.getText();

                // Validación de los campos
                if (Cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese la cédula para la busqueda.");
                    return;
                }

                // Búsqueda en la base de datos
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiantes1", "root", "123456");
                    String sql = "SELECT * FROM vehiculo WHERE cedula = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, Cedula);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        String cedula1 = rs.getString("cedula");
                        String nombre = rs.getString("nombre");
                        double b1 = rs.getDouble("b1");
                        double b2 = rs.getDouble("b2");
                        String combustible = rs.getString("combustible");
                        String color = rs.getString("color");
                        String propietario = rs.getString("propietario");

                        JOptionPane.showMessageDialog(null, "Vehiculo encontrado:\n" +
                                "Placa: " + placa + "\nMarca: " + marca + "\nCilindraje: " + cilindraje +
                                "\nCombustible: " + combustible + "\nColor: " + color +"\nPropietario: " + propietario);
                    } else {
                        int option = JOptionPane.showConfirmDialog(null, "Vehiculo no encontrado. ¿Desea registrar uno nuevo?",
                                "Vehículo no encontrado", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            JFrame registroFrame = new JFrame("Registro de Estudiantes");
                            registroFrame.setContentPane(new ingreso().jpanel);
                            registroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            registroFrame.pack();
                            registroFrame.setVisible(true);
                        }
                    }
                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar vehiculo: " + ex.getMessage());
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sistema de gestión de Carros");
                frame.setContentPane(new form1().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 700);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                // Cierra la ventana actual
                ((JFrame) SwingUtilities.getWindowAncestor(volverButton)).dispose();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Placa.setText(null);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Búsqueda de vehiculos");
        frame.setContentPane(new busqueda().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}