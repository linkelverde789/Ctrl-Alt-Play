package proyecto.sergio.demo.Repositorios.Videogames.Other.registerDownload;

import java.sql.*;

public class RepositoryRegisterDownload {
    private Connection connection;

    public RepositoryRegisterDownload(Connection connection) {
        this.connection = connection;
    }

    public int realizarRegistro(int UserID, int VideogameID) throws Exception {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("insert into registro (id_rom, id_usuario) values (?,?);", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, VideogameID);
            preparedStatement.setInt(2, UserID);
            if (preparedStatement.executeUpdate() == 0) {
                throw new Exception("No se ha podido insertar el registro");
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (!resultSet.next()) {
                    throw new Exception("No se ha podido insertar el registro");
                }
                return resultSet.getInt(1);
            }
        }
    }
}
