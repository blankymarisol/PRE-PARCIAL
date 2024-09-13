package umg.demostracion.DataBase.Service;


import umg.demostracion.DataBase.Dao.DatosDAO;
import umg.demostracion.DataBase.Model.TbDatos;

import java.sql.SQLException;
import java.util.List;

public class DatosService {
    private DatosDAO tbDatosDAO = new DatosDAO();

    public boolean insertarDato(TbDatos dato) {
        return tbDatosDAO.insertar(dato);
    }

    public List<TbDatos> obtenerTodosLosDatos() {
        return tbDatosDAO.obtenerTodos();
    }
    public TbDatos obtenerPorId(int id) throws SQLException {
        return tbDatosDAO.obtenerPorId(id);
    }

    public boolean actualizarDato(TbDatos dato) {
        return tbDatosDAO.actualizar(dato);
    }

    public boolean eliminarDato(int codigo) {
        return tbDatosDAO.eliminar(codigo);
    }
}
