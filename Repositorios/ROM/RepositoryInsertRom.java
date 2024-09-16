package proyecto.sergio.demo.Repositorios.ROM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RepositoryInsertRom {
    private final Connection connection;

    public RepositoryInsertRom(Connection connection) {
        this.connection = connection;
    }

    public int insertRom(RomEntity romEntity) throws Exception{
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("insert into roms (id_juego, id_consola,nombre) values (?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, romEntity.getGameID());
            preparedStatement.setInt(2, romEntity.getConsoleID());
            preparedStatement.setString(3, romEntity.getName());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        }
    }
}
