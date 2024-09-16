package proyecto.sergio.demo.Repositorios.User.read;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositoryReadUser {
    private Connection connection;

    public RepositoryReadUser(Connection connection) {
        this.connection = connection;
    }


    public List<UserEntity> readUser() throws Exception {
        List<UserEntity> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select id, id_rol, nombre, usuario, password, correo from usuarios;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new UserEntity(resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)));
                }
                return result;
            }
        }
    }
}
