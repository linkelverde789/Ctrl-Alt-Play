package proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Normal;

import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.VideogameEntity;

import java.util.List;

public class ServiceWebListGame implements IServiceWebListGame {
    private RepositoryListWebGame repositoryListWebGame;

    public ServiceWebListGame(RepositoryListWebGame repositoryListWebGame) {
        this.repositoryListWebGame = repositoryListWebGame;
    }

    @Override
    public List<VideogameEntity> videogamesList() throws Exception {
        return this.repositoryListWebGame.getVideogamesList();
    }
}
