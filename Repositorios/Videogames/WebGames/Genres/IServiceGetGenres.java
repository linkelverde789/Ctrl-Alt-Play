package proyecto.sergio.demo.Repositorios.Videogames.WebGames.Genres;

import java.util.HashMap;

public interface IServiceGetGenres {

    HashMap<Integer, String> getGenres() throws Exception;
}
