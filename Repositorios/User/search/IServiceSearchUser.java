package proyecto.sergio.demo.Repositorios.User.search;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

public interface IServiceSearchUser {

    UserEntity searchUser(int id) throws Exception;
}
