package proyecto.sergio.demo.Repositorios.User.ID;

import java.util.List;

public class ServiceGetID implements iServiceGetID{
    private final RepositoryGetID repositoryGetID;

    public ServiceGetID(RepositoryGetID repositoryGetID) {
        this.repositoryGetID = repositoryGetID;
    }


    @Override
    public List<Integer> getIDs() throws Exception {
        return this.repositoryGetID.getID();
    }
}
