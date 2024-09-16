package proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Texto;

import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.VideogameEntity;

import java.util.List;

public class ServiceWebListTextGame implements IServiceWebListTextGame {
    private RepositoryListTextWebGame repositoryListTextWebGame;

    public ServiceWebListTextGame(RepositoryListTextWebGame repositoryListTextWebGame) {
        this.repositoryListTextWebGame = repositoryListTextWebGame;
    }

    @Override
    public List<VideogameEntity> videogamesList(String texto) throws Exception {
        return this.repositoryListTextWebGame.getVideogamesList(texto);
    }
}
