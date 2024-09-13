package umg.demostracion.Formularios;

import umg.demostracion.DataBase.Model.TbDatos;
import umg.demostracion.DataBase.Service.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datos extends JFrame {
    private JPanel Formulario3;
    private JLabel lblIdUsuario;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblDepartamento;
    private JLabel lblFecha;
    private JTextField textFieldID;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldDepartamento;
    private JTextField textFieldNacimiento;
    private JButton buttonCrear;
    private JButton buttonBuscar;
    private JButton buttonActualizar;
    private JButton buttonEliminar;

    public Datos(){
    setContentPane(Formulario3);
    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TbDatos tbDatos = new TbDatos();
                tbDatos.setNombre(textFieldNombre.getText());
                tbDatos.setApellido(textFieldApellido.getText());
                tbDatos.setDepartamento(textFieldDepartamento.getText());
                String fechaTexto = textFieldNacimiento.getText();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); // Ajusta el formato según el input
                try {
                    try {
                        Date fechaUtil = formato.parse(fechaTexto);
                        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
                        tbDatos.setFechaNacimiento(fechaSql);
                        System.out.println("Fecha convertida: " + fechaUtil);
                    } catch (ParseException ex) {
                        System.out.println("Error: Formato de fecha incorrecto.");
                        JOptionPane.showMessageDialog(null, "Error: Formato de fecha incorrecto.");
                        return; // Salir del método si hay un error de fecha
                    }

                    // Bloque para la inserción en la base de datos
                    new DatosService().insertarDato(tbDatos);
                    JOptionPane.showMessageDialog(null, "Datos ingresado");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar datos: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idDato = textFieldID.getText().isEmpty() ? 0 : Integer.parseInt(textFieldID.getText());
                try{
                    TbDatos UsuarioEncontrado = new DatosService().obtenerPorId(idDato);
                    if (UsuarioEncontrado != null) {
                        textFieldID.setText(Integer.toString(UsuarioEncontrado.getCodigo()));
                        textFieldNombre.setText(UsuarioEncontrado.getNombre());
                        textFieldApellido.setText(UsuarioEncontrado.getApellido());
                        textFieldDepartamento.setText(UsuarioEncontrado.getDepartamento());
                        Date date = UsuarioEncontrado.getFechaNacimiento();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaTexto = sdf.format(date);
                        textFieldNacimiento.setText(fechaTexto);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro el Usuario");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Error al obtener el usuario"+ ex.getMessage());
                }
            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(textFieldID.getUIClassID());
                    String nombre = textFieldNombre.getText();
                    String apellido = textFieldApellido.getText();
                    String departamento = textFieldDepartamento.getText();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date utilDate = sdf.parse(textFieldNacimiento.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                    TbDatos dato = new TbDatos(codigo, nombre, apellido, departamento, sqlDate);
                    DatosService tbDatosService = new DatosService();

                    boolean actualizado = tbDatosService.actualizarDato(dato);

                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número válido");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Formato de fecha inválido. Use dd/MM/yyyy");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
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

                    int codigo = Integer.parseInt(idText);

                    // Confirmación antes de eliminar
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de que desea eliminar el registro con ID " + codigo + "?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        DatosService tbDatosService = new DatosService();
                        boolean eliminado = tbDatosService.eliminarDato(codigo);

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
                textFieldApellido.setText("");
                textFieldDepartamento.setText("");
                textFieldNacimiento.setText("");
            }
        });
    }
}


