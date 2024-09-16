package proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Normal;

import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.VideogameEntity;

import java.util.List;

public interface IServiceWebListGame {
    List<VideogameEntity> videogamesList() throws Exception;
}
