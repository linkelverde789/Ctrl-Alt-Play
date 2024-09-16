package proyecto.sergio.demo.Repositorios.Videogames.WebGames.Genres;

import java.util.HashMap;

public class ServiceGetGenres implements IServiceGetGenres{
    private RepositoryGetGenres repositoryGetGenres;

    public ServiceGetGenres(RepositoryGetGenres repositoryGetGenres) {
        this.repositoryGetGenres = repositoryGetGenres;
    }

    @Override
    public HashMap<Integer, String> getGenres() throws Exception {
        return this.repositoryGetGenres.getGenres();
    }

}
