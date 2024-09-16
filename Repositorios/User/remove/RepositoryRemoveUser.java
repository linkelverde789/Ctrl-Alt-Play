package proyecto.sergio.demo.Repositorios.User.remove;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RepositoryRemoveUser {
    private Connection connection;

    public RepositoryRemoveUser(Connection connection) {
        this.connection = connection;
    }

    public boolean deleteUser(int id) throws Exception{
        try(PreparedStatement preparedStatement=this.connection.prepareStatement("delete from usuarios where id=?")){
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;
        }
    }
}
