package proyecto.sergio.demo.Repositorios.Videogames.Other.searchID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositorySearchGameID {
    private Connection connection;

    public RepositorySearchGameID(Connection connection) {
        this.connection = connection;
    }

    public VideogameEntity buscarVideojuego(int idBusqueda) throws Exception {
        String sql = "select videojuegos.id, titulo, descripcion, estudios.nombre, distribuidores.nombre " +
                "from videojuegos inner join estudios on (videojuegos.id_estudio=estudios.id) " +
                "inner join distribuidores on (videojuegos.id_distribuidora=distribuidores.id) " +
                "where videojuegos.id = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idBusqueda);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new Exception("Videojuego no encontrado.");
                }
                return new VideogameEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }
        }
    }
}
