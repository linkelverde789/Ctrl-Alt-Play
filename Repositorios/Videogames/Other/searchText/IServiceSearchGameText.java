package proyecto.sergio.demo.Repositorios.Videogames.Other.searchText;

import java.util.List;

public interface IServiceSearchGameText {
    List<VideogameEntity> searchGameText(String text) throws Exception;
}
