package umg.demostracion.Formularios;

import umg.demostracion.DataBase.Model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JPanel jpanelprincipal;
    public static void main(String[] args) {
        JFrame frame = new JFrame("frmPanelPrincipal");
        frame.setContentPane(new Principal().jpanelprincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private JButton buttonejer1;
    private JButton buttonejer2;
    private JButton buttonejer3;

    public Principal() {
        buttonejer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuarios usuarios = new Usuarios();
                usuarios.setVisible(true);
                setVisible(false);
            }
        });
        buttonejer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datos datos = new Datos();
                datos.setVisible(true);
                setVisible(false);
            }
        });
        buttonejer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Champions champions = new Champions();
                champions.setVisible(true);
                setVisible(false);
            }
        });
    }
}
