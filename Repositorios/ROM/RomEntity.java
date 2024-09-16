package proyecto.sergio.demo.Repositorios.ROM;

public class RomEntity {
    private final String name;
    private final int romID;
    private final int consoleID;
    private final int gameID;

    public RomEntity(String name, int romID, int consoleID, int gameID) {
        this.name = name;
        this.romID = romID;
        this.consoleID = consoleID;
        this.gameID = gameID;
    }

    public String getName() {
        return name;
    }

    public int getRomID() {
        return romID;
    }

    public int getConsoleID() {
        return consoleID;
    }

    public int getGameID() {
        return gameID;
    }
}
