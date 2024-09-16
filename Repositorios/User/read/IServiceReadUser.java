package proyecto.sergio.demo.Repositorios.User.read;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

import java.util.List;

public interface IServiceReadUser {

    List<UserEntity> readUser() throws Exception;
}
