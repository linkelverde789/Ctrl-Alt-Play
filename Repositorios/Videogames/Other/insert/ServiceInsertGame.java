package proyecto.sergio.demo.Repositorios.Videogames.Other.insert;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;

public class ServiceInsertGame implements IServiceInsertGame {
    private RepositoryInsertGame repositoryInsertGame;

    public ServiceInsertGame(RepositoryInsertGame repositoryInsertGame) {
        this.repositoryInsertGame = repositoryInsertGame;
    }

    @Override
    public int insertGame(VideogameEntity videogame) throws Exception {
        if(videogame ==null){throw new Exception("El videojuego no ha sido iniciado.");}
        if(videogame.getTitle()==null|| videogame.getTitle().isBlank()|| videogame.getTitle().length()<3){throw new Exception("El campo 'título' no es correcto.");}
        if(videogame.getStudioID()<1){throw new Exception("Campo 'ID Estudio' no es correcto.");}
        if(videogame.getPublisherID()<1){throw new Exception("Campo 'ID Distribuidora' no es correcto.");}
        return this.repositoryInsertGame.insertGame(videogame);
    }
}
