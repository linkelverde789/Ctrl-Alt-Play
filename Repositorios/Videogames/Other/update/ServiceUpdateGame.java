package proyecto.sergio.demo.Repositorios.Videogames.Other.update;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;

public class ServiceUpdateGame implements IServiceUpdateGame {
    private RepositoryUpdateGame repositoryUpdateGame;

    public ServiceUpdateGame(RepositoryUpdateGame repositoryUpdateGame) {
        this.repositoryUpdateGame = repositoryUpdateGame;
    }

    @Override
    public boolean actualizarVideojuegos(VideogameEntity videojuego) throws Exception {
        if(videojuego==null){throw new Exception("El videojuego no ha sido iniciado.");}
        if(videojuego.getTitle()==null||videojuego.getTitle().isBlank()||videojuego.getTitle().length()<3){throw new Exception("El campo 'título' no es correcto.");}
        if(videojuego.getStudioID()<1){throw new Exception("Campo 'ID Estudio' no es correcto.");}
        if(videojuego.getPublisherID()<1){throw new Exception("Campo 'ID Distribuidora' no es correcto.");}
        if(videojuego.getID()<1){throw new Exception("Campo 'ID' no puede ser menor de 1");}
        return this.repositoryUpdateGame.actualizarVideojuegos(videojuego);
    }

}
