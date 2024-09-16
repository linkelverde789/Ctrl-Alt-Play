package proyecto.sergio.demo.Repositorios.Videogames.WebGames.JuegoCompleto;

import java.util.List;

public class ServiceJuegoCompleto implements IServiceJuegoCompleto {
    private RepositoryJuegoCompleto repositoryJuegoCompleto;

    public ServiceJuegoCompleto(RepositoryJuegoCompleto repositoryJuegoCompleto) {
        this.repositoryJuegoCompleto = repositoryJuegoCompleto;
    }

    @Override
    public VideogameEntity fullGame(int id) throws Exception {
        return this.repositoryJuegoCompleto.obtenerJuegoCompleto(id);
    }
}
