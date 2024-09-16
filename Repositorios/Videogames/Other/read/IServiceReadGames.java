package proyecto.sergio.demo.Repositorios.Videogames.Other.read;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;

import java.util.List;

public interface IServiceReadGames {

    List<VideogameEntity> readGames() throws Exception;
}
