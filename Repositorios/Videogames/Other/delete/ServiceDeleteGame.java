package proyecto.sergio.demo.Repositorios.Videogames.Other.delete;

public class ServiceDeleteGame implements IServiceDeleteGame {
    private RepositoryDeleteGame repositoryDeleteGame;

    public ServiceDeleteGame(RepositoryDeleteGame repositoryDeleteGame) {
        this.repositoryDeleteGame = repositoryDeleteGame;
    }

    @Override
    public boolean removeGame(int idBusqueda) throws Exception {
        if (idBusqueda <= 0) {
            throw new Exception("Campo 'ID' no puede ser menor o igual a 0");
        }
        return this.repositoryDeleteGame.removeGame(idBusqueda);
    }

}
