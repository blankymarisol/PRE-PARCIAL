package umg.demostracion.DataBase.Service;

import umg.demostracion.DataBase.Dao.ChampionsDAO;
import umg.demostracion.DataBase.Model.EquipoChampions;

import java.util.List;

public class ChampionsService {
    private ChampionsDAO equipoDAO = new ChampionsDAO();

    public boolean insertarEquipo(EquipoChampions equipo) {
        return equipoDAO.insertar(equipo);
    }

    public List<EquipoChampions> obtenerTodosLosEquipos() {
        return equipoDAO.obtenerTodos();
    }

    public boolean actualizarEquipo(EquipoChampions equipo) {
        return equipoDAO.actualizar(equipo);
    }

    public boolean eliminarEquipo(int idEquipo) {
        return equipoDAO.eliminar(idEquipo);
    }
}
