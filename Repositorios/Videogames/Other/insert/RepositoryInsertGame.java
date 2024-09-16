package proyecto.sergio.demo.Repositorios.Videogames.Other.insert;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RepositoryInsertGame {
    private Connection connection;

    public RepositoryInsertGame(Connection connection) {
        this.connection = connection;
    }

    public int insertGame(VideogameEntity videogame) throws Exception {
        boolean saveAutocommit = this.connection.getAutoCommit();
        this.connection.setAutoCommit(false);
        try {
            int idJuego;
            try (PreparedStatement preparedStatement = this.connection.prepareStatement("insert into videojuegos (id_estudio, id_distribuidora, titulo, descripcion) values (?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, videogame.getPublisherID());
                preparedStatement.setInt(2, videogame.getStudioID());
                preparedStatement.setString(3, videogame.getTitle());
                preparedStatement.setString(4, videogame.getDescription());

                if (!existsInTable("estudios", "id", videogame.getStudioID())) {
                    throw new Exception("El id_estudio " + videogame.getStudioID() + " no existe.");
                }

                if (!existsInTable("distribuidoras", "id", videogame.getPublisherID())) {
                    throw new Exception("El id_distribuidora " + videogame.getPublisherID() + " no existe.");
                }

                if(preparedStatement.executeUpdate()>0){
                    throw  new Exception("No se ha podido ingresar el juego: " + videogame.getTitle());
                }
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (!resultSet.next()) {
                        throw new Exception("No se ha podido ingresar el juego: " + videogame.getTitle());
                    }
                    idJuego = resultSet.getInt(1);
                }
                if (!videogame.getImages().isEmpty()) {
                    try (PreparedStatement preparedStatementFotos = this.connection.prepareStatement("insert into fotos (id_juego, foto) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                        for (String item : videogame.getImages()) {
                            preparedStatementFotos.setInt(1, idJuego);
                            preparedStatementFotos.setString(2, item);
                            preparedStatementFotos.executeUpdate();
                            try (ResultSet resultSet = preparedStatementFotos.getGeneratedKeys()) {
                                if(!resultSet.next()){
                                    throw new Exception("No se ha podido ingresar la foto: "+item);
                                }
                            }
                        }
                    }
                }
            }
            this.connection.commit();
            return idJuego;
        } catch (Exception ex) {
            this.connection.rollback();
            throw ex;
        } finally {
            this.connection.setAutoCommit(saveAutocommit);
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
