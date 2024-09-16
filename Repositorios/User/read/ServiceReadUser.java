package proyecto.sergio.demo.Repositorios.User.read;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

import java.util.List;

public class ServiceReadUser implements IServiceReadUser {
    private RepositoryReadUser repositoryReadUser;
    public ServiceReadUser(RepositoryReadUser repositoryReadUser) {
        this.repositoryReadUser = repositoryReadUser;
    }
    @Override
    public List<UserEntity> readUser() throws Exception {
        return this.repositoryReadUser.readUser();
    }
}
