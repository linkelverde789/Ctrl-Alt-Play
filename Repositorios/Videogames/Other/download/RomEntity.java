package proyecto.sergio.demo.Repositorios.Videogames.Other.download;

public class RomEntity {
    private String rom_title;
    private String consoleName;
    private int ID;

    public RomEntity( int ID,String rom_title, String consoleName) {
        this.rom_title = rom_title;
        this.consoleName = consoleName;
        this.ID = ID;
    }

    public String getRom_title() {
        return rom_title;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public int getID() {
        return ID;
    }
}
