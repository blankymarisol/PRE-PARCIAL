package umg.demostracion.Formularios;

import umg.demostracion.DataBase.Model.Usuario;
import umg.demostracion.DataBase.Service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Usuarios extends JFrame {
    private JPanel Formulario2;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario Usuarios");
        frame.setContentPane(new Usuarios().Formulario2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private JLabel lblIdusuario;
    private JLabel lblNombre;
    private JLabel lblCarne;
    private JLabel lblCorreo;
    private JTextField textFieldID;
    private JTextField textFieldNombre;
    private JTextField textFieldCarne;
    private JTextField textFieldCorreo;
    private JButton buttonIngresar;
    private JButton buttonActualizar;
    private JButton buttonBuscar;
    private JButton buttonEliminar;

    public Usuarios() {
        setContentPane(Formulario2);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buttonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario user = new Usuario();
                user.setNombre(textFieldNombre.getText());
                user.setCarne(textFieldCarne.getText());
                user.setCorreo(textFieldCorreo.getText());
                try {
                    if (new UsuarioService().checkEmailDuplicated(user.getCorreo())) {
                        JOptionPane.showMessageDialog(null, "El correo ya está en uso. Por favor, utiliza otro correo.");
                        return; // Salir si el correo ya existe
                    }
                    else {
                        new UsuarioService().createUser(user);
                        JOptionPane.showMessageDialog(null, "Usuario creado");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(textFieldID.getText());
                    String nombre = textFieldNombre.getText();
                    String carne = textFieldCarne.getText();
                    String correo = textFieldCorreo.getText();
                    Usuario user = new Usuario(codigo, nombre, carne, correo);
                    UsuarioService usuarioService = new UsuarioService();

                    boolean actualizado = usuarioService.actualizarUser(user);

                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número válido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
                }
            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carneUsuario = textFieldCarne.getText().isEmpty() ? null : (textFieldCarne.getText());
                try{
                    Usuario UsuarioEncontrado = new UsuarioService().getUserByCarne(carneUsuario);
                    if (UsuarioEncontrado != null) {
                        textFieldID.setText(Integer.toString(UsuarioEncontrado.getId()));
                        textFieldNombre.setText(UsuarioEncontrado.getNombre());
                        textFieldCarne.setText(UsuarioEncontrado.getCarne());
                        textFieldCorreo.setText(UsuarioEncontrado.getCorreo());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro el Usuario");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Error al obtener el usuario"+ ex.getMessage());
                }

            }
        });
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Asumimos que hay un campo de texto para el ID
                    String idText = textFieldID.getText();

                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID para eliminar.");
                        return;
                    }
                    // Confirmación antes de eliminar
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de que desea eliminar el registro con ID " + idText + "?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        UsuarioService userService = new UsuarioService();
                        boolean eliminado = userService.eliminarUser(idText);

                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");
                            // Opcionalmente, limpiar los campos después de eliminar
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número válido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
            }

            // Método para limpiar los campos después de eliminar
            private void limpiarCampos() {
                textFieldID.setText("");
                textFieldNombre.setText("");
                textFieldCarne.setText("");
                textFieldCorreo.setText("");
            }
        });

    }
}