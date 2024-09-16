package proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames.VideogameEntity;


public interface IServiceInsertFullGame {
    int insertGame(VideogameEntity videogameEntity) throws Exception;
}
