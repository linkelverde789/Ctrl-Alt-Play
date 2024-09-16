package proyecto.sergio.demo.Repositorios.User.search;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

public class ServiceSearchUser implements IServiceSearchUser {
    private RepositorySearchUser repositorySearchUser;

    public ServiceSearchUser(RepositorySearchUser repositorySearchUser) {
        this.repositorySearchUser = repositorySearchUser;
    }

    @Override
    public UserEntity searchUser(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Campo 'ID' no puede ser menor o igual a 0");
        }
        return this.repositorySearchUser.searchUser(id);
    }

}
