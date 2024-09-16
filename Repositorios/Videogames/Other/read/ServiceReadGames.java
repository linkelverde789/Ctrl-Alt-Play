package proyecto.sergio.demo.Repositorios.Videogames.Other.read;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;

import java.util.List;

public class ServiceReadGames implements IServiceReadGames {
    private RepositoryReadGames repositoryReadGames;
    public ServiceReadGames(RepositoryReadGames repositoryReadGames) {
        this.repositoryReadGames = repositoryReadGames;
    }
    @Override
    public List<VideogameEntity> readGames() throws Exception {
        return this.repositoryReadGames.readGames();
    }
}
