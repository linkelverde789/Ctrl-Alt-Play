package proyecto.sergio.demo.Repositorios.Videogames.Other.update;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositoryUpdateGame {
    private Connection connection;

    public RepositoryUpdateGame(Connection connection) {
        this.connection = connection;
    }

    public boolean actualizarVideojuegos(VideogameEntity videojuegos) throws Exception {
        if (!existsInTable("estudios", "id", videojuegos.getStudioID())) {
            throw new Exception("El id_estudio " + videojuegos.getStudioID() + " no existe.");
        }

        if (!existsInTable("distribuidores", "id", videojuegos.getPublisherID())) {
            throw new Exception("El id_distribuidor " + videojuegos.getPublisherID() + " no existe.");
        }

        try (PreparedStatement preparedStatement = this.connection.prepareStatement("update videojuegos set id_estudio=?, id_distribuidora=?, titulo=?, descripcion=? where id=?;")) {
            preparedStatement.setInt(1, videojuegos.getStudioID());
            preparedStatement.setInt(2, videojuegos.getPublisherID());
            preparedStatement.setString(3, videojuegos.getTitle());
            preparedStatement.setString(4, videojuegos.getDescription());
            preparedStatement.setInt(5, videojuegos.getID());
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
