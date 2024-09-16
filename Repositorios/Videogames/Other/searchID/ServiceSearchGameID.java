package proyecto.sergio.demo.Repositorios.Videogames.Other.searchID;


public class ServiceSearchGameID implements IServiceSearchGameID {
    private RepositorySearchGameID repositorySearchGameID;

    public ServiceSearchGameID(RepositorySearchGameID repositorySearchGameID) {
        this.repositorySearchGameID = repositorySearchGameID;
    }

    @Override
    public VideogameEntity buscarVideojuego(int idBusqueda) throws Exception {
        if (idBusqueda <= 0) {
            throw new Exception("Campo 'ID' no puede ser menor o igual a 0");
        }
        return this.repositorySearchGameID.buscarVideojuego(idBusqueda);
    }

}
