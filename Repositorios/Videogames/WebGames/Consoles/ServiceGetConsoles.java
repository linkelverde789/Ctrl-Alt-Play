package proyecto.sergio.demo.Repositorios.Videogames.WebGames.Consoles;

import java.util.HashMap;

public class ServiceGetConsoles implements IServiceGetConsoles {
    private RepositoryGetConsoles repositoryGetConsoles;

    public ServiceGetConsoles(RepositoryGetConsoles repositoryGetConsoles) {
        this.repositoryGetConsoles = repositoryGetConsoles;
    }

    @Override
    public HashMap<Integer, String> getConsoles() throws Exception {
        return this.repositoryGetConsoles.getConsoles();
    }

}
