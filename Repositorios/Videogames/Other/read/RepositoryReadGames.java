package proyecto.sergio.demo.Repositorios.Videogames.Other.read;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositoryReadGames {
    private Connection connection;

    public RepositoryReadGames(Connection connection) {
        this.connection = connection;
    }


    public List<VideogameEntity> readGames() throws Exception {
        List<VideogameEntity> videogamesList = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select id, id_estudio, id_distribuidora, titulo, descripcion from videojuegos;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    videogamesList.add(new VideogameEntity(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5)));
                }
                return videogamesList;
            }
        }
    }
}
