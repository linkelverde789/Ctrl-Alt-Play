package proyecto.sergio.demo.Repositorios.User.login;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositoryLogin {
    private Connection connection;

    public RepositoryLogin(Connection connection) {
        this.connection = connection;
    }

    public UserEntity login(String username, String password) throws Exception {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select id, id_rol,nombre,correo from usuarios where usuario = ? and password = hashpassword(?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new Exception("Usuario " + username + " no encontrado");
                }
                return new UserEntity(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(3),username,
                        password,resultSet.getString(4));
            }
        }
    }
}
