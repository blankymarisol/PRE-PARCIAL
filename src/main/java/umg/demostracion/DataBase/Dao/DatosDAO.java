package umg.demostracion.DataBase.Dao;

import umg.demostracion.DataBase.Conecction.DataBaseConnection;
import umg.demostracion.DataBase.Model.TbDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDAO {
    public boolean insertar(TbDatos dato) {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dato.getNombre());
            pstmt.setString(2, dato.getApellido());
            pstmt.setString(3, dato.getDepartamento());
            pstmt.setDate(4, dato.getFechaNacimiento());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<TbDatos> obtenerTodos() {
        List<TbDatos> datos = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TbDatos dato = new TbDatos();
                dato.setCodigo(rs.getInt("codigo"));
                dato.setNombre(rs.getString("nombre"));
                dato.setApellido(rs.getString("apellido"));
                dato.setDepartamento(rs.getString("departamento"));
                dato.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                datos.add(dato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }
    public TbDatos obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TbDatos(
                            rs.getInt("codigo"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("departamento"),
                            rs.getDate("fecha_nacimiento")
                    );
                }
            }
        }
        return null;
    }



    public boolean actualizar(TbDatos dato) {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dato.getNombre());
            pstmt.setString(2, dato.getApellido());
            pstmt.setString(3, dato.getDepartamento());
            pstmt.setDate(4, dato.getFechaNacimiento());
            pstmt.setInt(5, dato.getCodigo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
