package proyecto.sergio.demo.Repositorios.User.ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositoryGetID {
    private final Connection connection;

    public RepositoryGetID(Connection connection) {
        this.connection = connection;
    }

    public List<Integer> getID() throws Exception {
        List<Integer> result=new ArrayList<>();
        try(PreparedStatement preparedStatement=this.connection.prepareStatement("select id from usuarios;")){
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    result.add(resultSet.getInt(1));
                }
            }
        }
        return result;
    }
}
