package proyecto.sergio.demo.Repositorios.Videogames.WebGames.JuegoCompleto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RepositoryJuegoCompleto {
    private Connection connection;

    public RepositoryJuegoCompleto(Connection connection) {
        this.connection = connection;
    }

    public VideogameEntity obtenerJuegoCompleto(int id) throws Exception {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select titulo, descripcion, distribuidora, estudio, generos, consolas from vista_juegos where id=?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new Exception("No se encontro el titulo");
                }
                VideogameEntity videogame = new VideogameEntity(id, resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(3),
                        resultSet.getString(4));
                obtenerFotos(videogame);
                obtenerManuales(videogame);
                obtenerGuias(videogame);
                obtenerRoms(videogame);
                return videogame;
            }
        }
    }

    public void obtenerRoms(VideogameEntity videogame) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select id, nombre from roms where id_juego=?")) {
            preparedStatement.setInt(1, videogame.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    videogame.addRoms(resultSet.getInt(1), resultSet.getString(2));
                }
            }
        }
    }


    public void obtenerManuales(VideogameEntity videogame) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select manual from manuales where id_juego=?")) {
            preparedStatement.setInt(1, videogame.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    videogame.addManual(resultSet.getString(1));
                }
            }
        }
    }


    public void obtenerGuias(VideogameEntity videogame) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select guia from guias where id_juego=?")) {
            preparedStatement.setInt(1, videogame.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    videogame.addGuia(resultSet.getString(1));
                }
            }
        }
    }

    public void obtenerFotos(VideogameEntity videogame) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select foto from fotos where id_juego=?")) {
            preparedStatement.setInt(1, videogame.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    videogame.addFoto(resultSet.getString(1));
                }
            }
        }
    }

}
