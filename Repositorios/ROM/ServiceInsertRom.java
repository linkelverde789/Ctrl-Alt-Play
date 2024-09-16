package proyecto.sergio.demo.Repositorios.ROM;

public class ServiceInsertRom implements IServiceInsertRom{
    private final RepositoryInsertRom  repositoryInsertRom;

    public ServiceInsertRom(RepositoryInsertRom repositoryInsertRom) {
        this.repositoryInsertRom = repositoryInsertRom;
    }

    @Override
    public int insertRom(RomEntity romEntity) throws Exception {
        return this.repositoryInsertRom.insertRom(romEntity);
    }
}
