package proyecto.sergio.demo.Repositorios.Videogames.Other.searchText;

public class VideogameEntity {
    private int id;
    private String distribuidora;
    private String estudio;
    private String titulo;
    private String descripcion;

    public VideogameEntity(int id, String titulo,String descripcion,String estudio, String distribuidora  ) {
        this.id = id;
        this.distribuidora = distribuidora;
        this.estudio = estudio;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ID: " + id +", Distribuidora: " + distribuidora +", Estudio: " + estudio + ", Título: " + titulo + ", Descripción: " + descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public String getEstudio() {
        return estudio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
