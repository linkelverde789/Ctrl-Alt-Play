package proyecto.sergio.demo.Repositorios.Videogames.Other.searchText;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorySearchGameText {
    private final Connection connection;

    public RepositorySearchGameText(Connection connection) {
        this.connection = connection;
    }

    public List<VideogameEntity> searchGameText(String text) throws Exception {
        List<VideogameEntity> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select videojuegos.id, titulo, descripcion, estudios.nombre, distribuidores.nombre from videojuegos inner join estudios on (videojuegos.id_estudio=estudios.id) inner join distribuidores on (videojuegos.id_distribuidora=distribuidores.id) where titulo like ?;")) {
            preparedStatement.setString(1, "%" + text + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new VideogameEntity(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)));
                }
                if (result.isEmpty()) {
                    throw new Exception("No se han encontrado juegos con el patrón: " + text);
                }
                return result;
            }
        }
    }
}
