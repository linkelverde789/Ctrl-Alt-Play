package proyecto.sergio.demo.Repositorios.Videogames.Other.insert;

import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;

public interface IServiceInsertGame {
    int insertGame(VideogameEntity videogame) throws Exception;
}
