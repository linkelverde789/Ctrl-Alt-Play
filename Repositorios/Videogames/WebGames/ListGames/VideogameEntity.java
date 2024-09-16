package proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames;


public class VideogameEntity {
    private int ID;
    private String titulo;
   private String estudio;
   private String distribuidor;
   private String consolas;
   private String generos;

    public VideogameEntity(int ID, String titulo, String estudio, String distribuidor, String consolas, String generos) {
        this.ID = ID;
        this.titulo = titulo;
        this.estudio = estudio;
        this.distribuidor = distribuidor;
        this.consolas = consolas;
        this.generos = generos;
    }

    public int getID() {
        return ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEstudio() {
        return estudio;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public String getConsolas() {
        return consolas;
    }

    public String getGeneros() {
        return generos;
    }

    @Override
    public String toString() {
        return "VideogameEntity{" +
                "ID=" + ID +
                ", titulo='" + titulo + '\'' +
                ", estudio='" + estudio + '\'' +
                ", distribuidor='" + distribuidor + '\'' +
                ", consolas='" + consolas + '\'' +
                ", generos='" + generos + '\'' +
                '}';
    }
}
