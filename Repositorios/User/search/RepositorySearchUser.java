package proyecto.sergio.demo.Repositorios.User.search;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositorySearchUser {
    private Connection connection;

    public RepositorySearchUser(Connection connection) {
        this.connection = connection;
    }

    public UserEntity searchUser(int id) throws Exception {
        String sql = "select id, id_rol, nombre, usuario, password, correo from usuarios where id = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new Exception("Usuario no encontrado.");
                }
                return new UserEntity(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
        }
    }
}
