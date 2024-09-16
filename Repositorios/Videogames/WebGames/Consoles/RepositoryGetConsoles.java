package proyecto.sergio.demo.Repositorios.Videogames.WebGames.Consoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class RepositoryGetConsoles {
    private Connection connection;

    public RepositoryGetConsoles(Connection connection) {
        this.connection = connection;
    }


    public HashMap<Integer, String> getConsoles() throws Exception {
        HashMap<Integer, String> consoles = new HashMap<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select id, nombre from consolas;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    consoles.put(resultSet.getInt(1), resultSet.getString(2));
                }
                return consoles;
            }
        }
    }
}
