package proyecto.sergio.demo.Repositorios.Videogames.Other.searchText;


import java.util.List;

public class ServiceSearchGameText implements IServiceSearchGameText{
    private final RepositorySearchGameText repositorySearchGameText;

    public ServiceSearchGameText(RepositorySearchGameText repositorySearchGameText) {
        this.repositorySearchGameText = repositorySearchGameText;
    }

    @Override
    public List<VideogameEntity> searchGameText(String text) throws Exception {
        return this.repositorySearchGameText.searchGameText(text);
    }
}
