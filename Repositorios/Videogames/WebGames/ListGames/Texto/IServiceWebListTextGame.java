package proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Texto;

import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.VideogameEntity;

import java.util.List;

public interface IServiceWebListTextGame {
    List<VideogameEntity> videogamesList(String text) throws Exception;
}
