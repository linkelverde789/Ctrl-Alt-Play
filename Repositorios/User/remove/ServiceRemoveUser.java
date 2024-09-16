package proyecto.sergio.demo.Repositorios.User.remove;

public class ServiceRemoveUser implements IServiceRemoveUser {
    private RepositoryRemoveUser repositorioEliminarUsuario;

    public ServiceRemoveUser(RepositoryRemoveUser repositorioEliminarUsuario) {
        this.repositorioEliminarUsuario = repositorioEliminarUsuario;
    }

    @Override
    public boolean deleteUser(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Campo 'ID' no puede ser menor o igual a 0");
        }
        return this.repositorioEliminarUsuario.deleteUser(id);
    }

}
