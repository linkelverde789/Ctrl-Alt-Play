package proyecto.sergio.demo.poolConexiones;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;


public class Conexion {

    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setJdbcUrl("jdbc:postgresql://172.28.0.2:5432/bdjuegos");
            config.setUsername("app");
            config.setPassword("app");
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            config.setIdleTimeout(30000);
            config.setMaxLifetime(600000);
            config.setConnectionTimeout(30000);

            dataSource = new HikariDataSource(config);
        }catch (Exception e) {
            throw new RuntimeException("Error al inicializar la conexión a la base de datos", e);
        }
    }

    public static DataSource obtenerConexion() {
        return dataSource;
    }
}
