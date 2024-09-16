package proyecto.sergio.demo.Repositorios.User.create;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RepositoryCreateUser {
    private Connection connection;

    public RepositoryCreateUser(Connection connection) {
        this.connection = connection;
    }

    public int createUser(UserEntity user) throws Exception {
        if (!existsInTable("roles", "id", user.getID_Rol())) {
            throw new Exception("El id_rol " + user.getID_Rol() + " no existe.");
        }

        String sql = "insert into usuarios (id_rol, nombre, correo, password, usuario) values (?,?,?,?,?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, user.getID_Rol());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setString(3, user.getCorreo());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUsuario());
            if (preparedStatement.executeUpdate() == 0) {
                throw new Exception("No se ha podido ingresar al usuario");
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (!resultSet.next()) {
                    throw new Exception("Error al ingresar usuario.");
                }
                return resultSet.getInt(1);
            }
        }
    }

    private boolean existsInTable(String tableName, String columnName, int id) throws Exception {
        String query = "select 1 from " + tableName + " where " + columnName + " = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
