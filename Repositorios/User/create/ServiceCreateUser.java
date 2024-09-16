package proyecto.sergio.demo.Repositorios.User.create;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

public class ServiceCreateUser implements IServiceCreateUser {
    private RepositoryCreateUser repositoryCreateUser;

    public ServiceCreateUser(RepositoryCreateUser repositoryCreateUser) {
        this.repositoryCreateUser = repositoryCreateUser;
    }

    @Override
    public int createUser(UserEntity user) throws Exception {
        if(user==null){throw new Exception("El usuario no puede estar en blanco");}
        if(user.getNombre()==null||user.getNombre().length()<3||!user.getNombre().matches("[a-zA-Z]+")){throw new Exception("Campo 'Nombre' incorrecto.");}
        if(user.getPassword()==null||user.getPassword().length()<8){throw new Exception("Campo 'Contraseña' incorrecto.");}
        if(user.getUsuario()==null||user.getUsuario().length()<3){throw new Exception("Campo 'Usuario' incorrecto.");}
        if(user.getCorreo()==null||user.getCorreo().length()<5||!user.getCorreo().contains("@")){throw new Exception("Campo 'Correo' incorrecto.");}
        if(user.getID_Rol()!=1&&user.getID_Rol()!=2){throw new Exception("Campo 'Rol' incorrecto.");}

        return this.repositoryCreateUser.createUser(user);
    }

}
