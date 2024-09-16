package proyecto.sergio.demo.Repositorios.Videogames.Other.getID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryGetID {
    private final Connection conexion;

    public RepositoryGetID(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Integer> getID() throws Exception {
        List<Integer>result=new ArrayList<Integer>();
        try(PreparedStatement preparedStatement=this.conexion.prepareStatement("select id from videojuegos;")){
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    result.add(resultSet.getInt(1));
                }
            }
            return result;
        }
    }
}
