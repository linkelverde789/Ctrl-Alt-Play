package proyecto.sergio.demo.Repositorios.Videogames.Other.download;

import java.util.HashMap;

public interface IServiceDownloadGame {
    HashMap<Integer,RomEntity> getRomList(int idGame) throws Exception;
}
