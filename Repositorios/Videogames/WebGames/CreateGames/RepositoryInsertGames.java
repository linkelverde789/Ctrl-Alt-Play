package proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RepositoryInsertGames {
    private Connection connection;

    public RepositoryInsertGames(Connection connection) {
        this.connection = connection;
    }

    public int insertGame(VideogameEntity videogameEntity) throws SQLException {
        int videogameId;
        boolean saveAutocommit=connection.getAutoCommit();
        this.connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO videojuegos (id_distribuidora, id_estudio, titulo, descripcion) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, videogameEntity.getPublisherID());
            preparedStatement.setInt(2, videogameEntity.getStuidID());
            preparedStatement.setString(3, videogameEntity.getTitulo());
            preparedStatement.setString(4, videogameEntity.getDescripcion());

            if (preparedStatement.executeUpdate() == 0) {
                this.connection.rollback();
                throw new SQLException("Error al insertar el videojuego.");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (!resultSet.next()) {
                    this.connection.rollback();
                    throw new SQLException("Error al obtener el id del videojuego.");
                }
                videogameId = resultSet.getInt(1);
            }

            if(videogameEntity.getConsolas()!=null&&!videogameEntity.getConsolas().isEmpty()){
                insertConsoles(videogameId, videogameEntity.getConsolas());
            }
            if(videogameEntity.getGeneros()!=null&&!videogameEntity.getGeneros().isEmpty()){
                insertGenres(videogameId, videogameEntity.getGeneros());
            }
            if(videogameEntity.getGuias()!=null&&!videogameEntity.getGuias().isEmpty()){
                insertGuia(videogameId, videogameEntity.getGuias());
            }
            if(videogameEntity.getManuales()!=null&&!videogameEntity.getManuales().isEmpty()){
                insertManual(videogameId, videogameEntity.getManuales());
            }
            if(videogameEntity.getImagen()!=null&&!videogameEntity.getImagen().isEmpty()){
                insertFotos(videogameId, videogameEntity.getImagen());
            }

            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            throw e;
        } finally {
            this.connection.setAutoCommit(saveAutocommit);
        }
        return videogameId;
    }

    private void insertConsoles(int id, List<Integer> idList) throws SQLException {
        for (int item : idList) {
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO consolas_juegos (id_juego, id_consola) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, item);
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException("Failed to insert into consolas_juegos.");
                }
            }
        }
    }

    private void insertGenres(int id, List<Integer> idList) throws SQLException {
        for (int item : idList) {
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO generos_juegos (id_juego, id_genero) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, item);
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException("Failed to insert into generos_juegos.");
                }
            }
        }
    }

    private void insertFotos(int id, List<String> fotosList) throws SQLException {
        for (String item : fotosList) {
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO fotos (id_juego, foto) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, item);
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException("Failed to insert into fotos.");
                }
            }
        }
    }

    private void insertManual(int id, List<String> manualList) throws SQLException {
        for (String item : manualList) {
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO manuales (id_juego, manual) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, item);
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException("Failed to insert into manuales.");
                }
            }
        }
    }

    private void insertGuia(int id, List<String> guiaList) throws SQLException {
        for (String item : guiaList) {
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO guias (id_juego, guia) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, item);
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException("Failed to insert into guias.");
                }
            }
        }
    }
}
