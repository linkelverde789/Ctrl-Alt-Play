package proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames;


public class ServiceInsertGame implements IServiceInsertFullGame {


    private RepositoryInsertGames repositoryInsertGames;

    public ServiceInsertGame(RepositoryInsertGames repositoryInsertGames) {
        this.repositoryInsertGames = repositoryInsertGames;
    }

    @Override
    public int insertGame(VideogameEntity videogameEntity) throws Exception {
        return this.repositoryInsertGames.insertGame(videogameEntity);    }
}
