package umg.demostracion.DataBase.Service;

import umg.demostracion.DataBase.Conecction.DataBaseConnection;
import umg.demostracion.DataBase.Conecction.TransactionManager;
import umg.demostracion.DataBase.Dao.UsuarioDAO;
import umg.demostracion.DataBase.Model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDAO userDao = new UsuarioDAO();


    public boolean eliminarUser(String idtext) throws SQLException {
        return userDao.deleteUserById(idtext);
    }

    public boolean checkEmailDuplicated(String email) {
        return userDao.isEmailDuplicated(email);
    }

    public void createUser(Usuario user) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                userDao.insertUser(user);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }
    public boolean actualizarUser(Usuario user) throws SQLException {
        return userDao.updateUser(user);
    }


    public Usuario getUserByCarne(String Carne) throws SQLException {
        return userDao.getUserByCarne(Carne);
    }
}
