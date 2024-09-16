package proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Normal;

import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.VideogameEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryListWebGame {
    private Connection connection;

    public RepositoryListWebGame(Connection connection) {
        this.connection = connection;
    }

    public List<VideogameEntity> getVideogamesList() throws SQLException {
        List<VideogameEntity> videogamesList = new ArrayList<>();

        String query = "SELECT videojuegos.id, videojuegos.titulo, estudios.nombre AS estudio, distribuidores.nombre AS distribuidora, " +
                "COALESCE(STRING_AGG(DISTINCT consolas.nombre, ', '), '') AS consolas, " +
                "COALESCE(STRING_AGG(DISTINCT generos.nombre, ', '), '') AS generos " +
                "FROM videojuegos " +
                "LEFT JOIN consolas_juegos ON videojuegos.id = consolas_juegos.id_juego " +
                "LEFT JOIN consolas ON consolas.id = consolas_juegos.id_consola " +
                "LEFT JOIN generos_juegos ON videojuegos.id = generos_juegos.id_juego " +
                "LEFT JOIN generos ON generos.id = generos_juegos.id_genero " +
                "LEFT JOIN estudios ON estudios.id = videojuegos.id_estudio " +
                "LEFT JOIN distribuidores ON distribuidores.id = videojuegos.id_distribuidora " +
                "GROUP BY videojuegos.id, videojuegos.titulo, estudio, distribuidora";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String estudio = resultSet.getString("estudio");
                String distribuidora = resultSet.getString("distribuidora");
                String consolas = resultSet.getString("consolas");
                String generos = resultSet.getString("generos");

                VideogameEntity videojuego = new VideogameEntity(id, titulo, estudio, distribuidora, consolas, generos);
                videogamesList.add(videojuego);
            }
        }

        return videogamesList;
    }
}
