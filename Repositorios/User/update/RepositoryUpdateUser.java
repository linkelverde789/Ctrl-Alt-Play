package proyecto.sergio.demo.Repositorios.User.update;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositoryUpdateUser {
    private Connection connection;

    public RepositoryUpdateUser(Connection connection) {
        this.connection = connection;
    }

    public boolean updateUser(UserEntity user) throws Exception {
        if (!existsInTable("roles", "id", user.getID_Rol())) {
            throw new Exception("El id_rol " + user.getID_Rol() + " no existe.");
        }
        String sql = "update usuarios set id_rol = ?, nombre = ?, usuario = ?, password = ?, correo = ? where id = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getID_Rol());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setString(3, user.getUsuario());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getCorreo());
            preparedStatement.setInt(6, user.getID());
            return preparedStatement.executeUpdate() > 0;
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
