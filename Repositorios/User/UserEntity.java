package proyecto.sergio.demo.Repositorios.User;

public class UserEntity {
    private int ID;
    private int ID_Rol;
    private String nombre;
    private String usuario;
    private String password;
    private String correo;

    public UserEntity(int ID, int ID_Rol, String nombre, String usuario, String password, String correo) {
        this.ID = ID;
        this.ID_Rol = ID_Rol;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
    }

    public int getID() {
        return ID;
    }

    public int getID_Rol() {
        return ID_Rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "ID: " + ID +
                ", ID_Rol: " + ID_Rol +
                ", Nombre: " + nombre + '\'' +
                ", Nombre Usuario: " + usuario + '\'' +
                ", Contraseña: " + password + '\'' +
                ", Correo: " + correo;
    }
}
