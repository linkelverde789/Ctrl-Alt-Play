package proyecto.sergio.demo.Repositorios.User.login;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

public class ServiceLogin implements IServiceLogin {
    private RepositoryLogin repositoryLogin;

    public ServiceLogin(RepositoryLogin repositoryLogin) {
        this.repositoryLogin = repositoryLogin;
    }

    @Override
    public UserEntity login(String username, String password) throws Exception {
        if(username ==null){throw new Exception("Debes introducir el nombre de usuario");}
        if(password==null){throw new Exception("Debes introducir la contraseña");}
        return this.repositoryLogin.login(username,password);
    }
}
