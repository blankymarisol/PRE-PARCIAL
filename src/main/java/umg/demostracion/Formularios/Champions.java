package umg.demostracion.Formularios;

import umg.demostracion.DataBase.Service.ChampionsService;
import umg.demostracion.DataBase.Model.EquipoChampions;
import umg.demostracion.DataBase.Dao.ChampionsDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Champions extends JFrame {
    private JPanel Formulario4;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario Champions");
        frame.setContentPane(new Champions().Formulario4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblPais;
    private JLabel lblCiudad;
    private JLabel lblEstadio;
    private JLabel lblFundacion;
    private JLabel lblEntrenador;
    private JLabel lblWeb;
    private JLabel lblface;
    private JLabel lblTWT;
    private JLabel lblIG;
    private JTextField textFieldID;
    private JTextField textFieldNombre;
    private JTextField textFieldPais;
    private JTextField textFieldCiudad;
    private JTextField textFieldEstadio;
    private JTextField textFieldFundacion;
    private JTextField textFieldEntrenador;
    private JTextField textFieldWeb;
    private JTextField textFieldFace;
    private JTextField textFieldTWT;
    private JTextField textFieldIG;
    private JTextField textFieldPatrocinador;
    private JButton buttonAgregar;
    private JButton buttonEliminar;
    private JButton buttonActualizar;
    private JButton buttonBuscar;

    private ChampionsService service = new ChampionsService();
    EquipoChampions equipo = new EquipoChampions();

    public Champions() {
        setContentPane(Formulario4);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buttonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipo.setNombre(textFieldNombre.getText());
                equipo.setPais(textFieldPais.getText());
                equipo.setCiudad(textFieldCiudad.getText());
                equipo.setEstadio(textFieldEstadio.getText());
                equipo.setFundacion(Integer.parseInt(textFieldFundacion.getText()));
                equipo.setEntrenador(textFieldEntrenador.getText());
                equipo.setWebOficial(textFieldWeb.getText());
                equipo.setFacebook(textFieldFace.getText());
                equipo.setTwitter(textFieldTWT.getText());
                equipo.setInstagram(textFieldIG.getText());
                equipo.setPatrocinadorPrincipal(textFieldPatrocinador.getText());
                try {
                    service.insertarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el equipo: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipo.setIdEquipo(Integer.parseInt(textFieldID.getText()));
                equipo.setNombre(textFieldNombre.getText());
                equipo.setPais(textFieldPais.getText());
                equipo.setCiudad(textFieldCiudad.getText());
                equipo.setEstadio(textFieldEstadio.getText());
                equipo.setFundacion(Integer.parseInt(textFieldFundacion.getText()));
                equipo.setEntrenador(textFieldEntrenador.getText());
                equipo.setWebOficial(textFieldWeb.getText());
                equipo.setFacebook(textFieldFace.getText());
                equipo.setTwitter(textFieldTWT.getText());
                equipo.setInstagram(textFieldIG.getText());
                equipo.setPatrocinadorPrincipal(textFieldPatrocinador.getText());

                try {
                    service.actualizarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "Equipo actualizado exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el equipo: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEquipo = Integer.parseInt(textFieldID.getText());

                try {
                    service.eliminarEquipo(idEquipo);
                    JOptionPane.showMessageDialog(null, "Equipo eliminado exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el equipo: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            private void mostrarEquipoEnFormulario(EquipoChampions equipo) {
                textFieldID.setText(String.valueOf(equipo.getIdEquipo()));
                textFieldNombre.setText(equipo.getNombre());
                textFieldPais.setText(equipo.getPais());
                textFieldCiudad.setText(equipo.getCiudad());
                textFieldEstadio.setText(equipo.getEstadio());
                textFieldFundacion.setText(String.valueOf(equipo.getFundacion()));
                textFieldEntrenador.setText(equipo.getEntrenador());
                textFieldWeb.setText(equipo.getWebOficial());
                textFieldFace.setText(equipo.getFacebook());
                textFieldTWT.setText(equipo.getTwitter());
                textFieldIG.setText(equipo.getInstagram());
                textFieldPatrocinador.setText(equipo.getPatrocinadorPrincipal());
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBusqueda = JOptionPane.showInputDialog("Ingrese el nombre del equipo a buscar:");
                if (nombreBusqueda != null && !nombreBusqueda.isEmpty()) {
                    try {
                        List<EquipoChampions> equipos = service.obtenerTodosLosEquipos();

                        EquipoChampions equipoEncontrado = equipos.stream()
                                .filter(eq -> eq.getNombre().toLowerCase().contains(nombreBusqueda.toLowerCase()))
                                .findFirst()
                                .orElse(null);

                        if (equipoEncontrado != null) {
                            mostrarEquipoEnFormulario(equipoEncontrado);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró ningún equipo con ese nombre", "Equipo no encontrado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al buscar el equipo: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }

            }
        });
    }
}
