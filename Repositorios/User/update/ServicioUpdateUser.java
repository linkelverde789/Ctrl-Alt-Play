package proyecto.sergio.demo.Repositorios.User.update;

import proyecto.sergio.demo.Repositorios.User.UserEntity;

public class ServicioUpdateUser implements IServicioUpdateUser {
    private RepositoryUpdateUser repositoryUpdateUser;

    public ServicioUpdateUser(RepositoryUpdateUser repositoryUpdateUser) {
        this.repositoryUpdateUser = repositoryUpdateUser;
    }
    @Override
    public boolean updateUser(UserEntity user) throws Exception {
        if(user ==null){throw new Exception("El usuario no puede estar en blanco");}
        if(user.getNombre()==null|| user.getNombre().length()<3||!user.getNombre().matches("[a-zA-Z]+")){throw new Exception("Campo 'Nombre' incorrecto.");}
        if(user.getPassword()==null|| user.getPassword().length()<8){throw new Exception("Campo 'Contraseña' incorrecto.");}
        if(user.getUsuario()==null|| user.getUsuario().length()<3){throw new Exception("Campo 'Usuario' incorrecto.");}
        if(user.getCorreo()==null|| user.getCorreo().length()<5||!user.getCorreo().contains("@")){throw new Exception("Campo 'Correo' incorrecto.");}
        if(user.getID_Rol()<1||user.getID_Rol()>4){throw new Exception("Campo 'Rol' incorrecto.");}
        if(user.getID()<=0){throw new Exception("Campo 'ID' no puede ser igual o menor a 0");}
        return this.repositoryUpdateUser.updateUser(user);
    }

}
