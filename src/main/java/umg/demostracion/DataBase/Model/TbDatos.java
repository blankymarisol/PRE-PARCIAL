package umg.demostracion.DataBase.Model;

public class TbDatos {
    private int codigo;
    private String nombre;
    private String apellido;
    private String departamento;
    private java.sql.Date fechaNacimiento;

    // Constructor vacío
    public TbDatos() {}

    // Constructor con parámetros
    public TbDatos(int codigo, String nombre, String apellido, String departamento, java.sql.Date fechaNacimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
