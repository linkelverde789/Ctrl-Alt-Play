package proyecto.sergio.demo.Repositorios.Videogames.WebGames.Genres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class RepositoryGetGenres {
    private Connection connection;

    public RepositoryGetGenres(Connection connection) {
        this.connection = connection;
    }


    public HashMap<Integer, String> getGenres() throws Exception {
        HashMap<Integer, String> genres = new HashMap<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select id, nombre from generos;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    genres.put(resultSet.getInt(1), resultSet.getString(2));
                }
                return genres;
            }
        }
    }
}
