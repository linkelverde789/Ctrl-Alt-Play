package proyecto.sergio.demo.Repositorios.Videogames.Other.registerDownload;

public class ServiceRegisterDownload implements IServiceRegisterDownload {
    private RepositoryRegisterDownload repositoryRegisterDownload;

    public ServiceRegisterDownload(RepositoryRegisterDownload repositoryRegisterDownload) {
        this.repositoryRegisterDownload = repositoryRegisterDownload;
    }

    @Override
    public int downloadGame(int idUsuario, int idVideojuego) throws Exception {
        if(idVideojuego<1){throw new Exception("ID 'videojuego' no puede ser menor de 1");}
        if(idUsuario<1){throw new Exception("ID 'usuario' no puede ser menor de 1");}

        return this.repositoryRegisterDownload.realizarRegistro(idUsuario, idVideojuego);
    }
}
