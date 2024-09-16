package proyecto.sergio.demo.Repositorios.Videogames.Other.download;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class RepositoryDownloadGame {
    private Connection connection;

    public RepositoryDownloadGame(Connection connection) {
        this.connection = connection;
    }

    public HashMap<Integer, RomEntity> getRomList(int idGame) throws Exception {
        if (!existsInTable("videojuegos", "id", idGame)) {
            throw new Exception("El id_juego " + idGame + " no existe.");
        }

        HashMap<Integer, RomEntity> result = new HashMap<>();
        String sql = "select roms.id, roms.nombre, consolas.nombre from roms " +
                "inner join consolas on (roms.id_consola=consolas.id) " +
                "where roms.id_juego=?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idGame);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.put(result.size() + 1, new RomEntity(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
                }
                return result;
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
