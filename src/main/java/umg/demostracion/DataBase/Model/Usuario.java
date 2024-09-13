package umg.demostracion.DataBase.Model;

public class Usuario {
    private int id;

    public Usuario(int codigo, String nombre, String carne, String correo) {
    }

    public String getCarne() {
        return carne;
    }
    public Usuario(){}
    public Usuario(int user_id, String carne, String nombre, String correo, String seccion) {
        this.id = user_id;
        this.carne = carne;
        this.nombre = nombre;
        this.correo = correo;
        this.seccion = seccion;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public long getTelegramid() {
        return telegramid;
    }

    public void setTelegramid(long telegramid) {
        this.telegramid = telegramid;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    private String carne;
    private String nombre;
    private String correo;
    private String seccion;
    private long telegramid;
    private String activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {

    }

    public void setVisible(boolean b) {
    }
}
