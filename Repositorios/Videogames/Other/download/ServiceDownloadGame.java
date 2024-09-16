package proyecto.sergio.demo.Repositorios.Videogames.Other.download;

import java.util.HashMap;

public class ServiceDownloadGame implements IServiceDownloadGame{
    private RepositoryDownloadGame repositoryDownloadGame;

    public ServiceDownloadGame(RepositoryDownloadGame repositoryDownloadGame) {
        this.repositoryDownloadGame = repositoryDownloadGame;
    }

    @Override
    public HashMap<Integer, RomEntity> getRomList(int idGame) throws Exception {
        if(idGame<1){throw new Exception("El ID del juego es inválido.");}
        return this.repositoryDownloadGame.getRomList(idGame);
    }
}
