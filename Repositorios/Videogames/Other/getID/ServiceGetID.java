package proyecto.sergio.demo.Repositorios.Videogames.Other.getID;

import java.util.List;

public class ServiceGetID implements IServiceGetID{
    private final RepositoryGetID repositoryGetID;

    public ServiceGetID(RepositoryGetID repositoryGetID) {
        this.repositoryGetID = repositoryGetID;
    }

    @Override
    public List<Integer> getID() throws Exception {
        return this.repositoryGetID.getID();
    }
}
