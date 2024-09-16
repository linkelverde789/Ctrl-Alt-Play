package proyecto.sergio.demo.Repositorios.User.login;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

public interface IServiceLogin {

    UserEntity login(String username, String password) throws Exception;
}
