package proyecto.sergio.demo.Repositorios.Videogames.Other.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositoryDeleteGame {
    private Connection connection;

    public RepositoryDeleteGame(Connection connection) {
        this.connection = connection;
    }

    public boolean removeGame(int id) throws Exception{
        try(PreparedStatement preparedStatement=this.connection.prepareStatement("delete from videojuegos where id=?")){
            preparedStatement.setInt(1,id);
            if(!existsInTable("videojuegos", "id",id)){
                throw new Exception("El ID " + id + " no existe.");
            }
            return preparedStatement.executeUpdate()>0;
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
