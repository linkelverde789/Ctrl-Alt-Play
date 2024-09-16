package proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames;

import java.util.ArrayList;
import java.util.List;

public class VideogameEntity {
    private int id;
    private String titulo;
    private String descripcion;
    private List<String> imagen;
    private List<Integer> generos;
    private List<Integer> consolas;
    private List<String> roms;
    private int publisherID;
    private int stuidID;
    private List<String> manuales;
    private List<String> guias;


    public VideogameEntity(int id, String titulo, String descripcion, int publisherID, int stuidID) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.publisherID = publisherID;
        this.stuidID = stuidID;
        this.imagen = new ArrayList<>();
        this.roms = new ArrayList<>();
        this.generos = new ArrayList<>();
        this.consolas = new ArrayList<>();
        this.manuales = new ArrayList<>();
        this.guias = new ArrayList<>();
    }

    public List<String> getManuales() {
        return manuales;
    }

    public List<String> getGuias() {
        return guias;
    }

    public void addManual(String manual) {
        this.manuales.add(manual);
    }

    public void addGuia(String guia) {
        this.guias.add(guia);
    }

    public void addRoms(String rom) {
        this.roms.add(rom);
    }

    public List<String> getRoms() {
        return roms;
    }

    public void addConsoleID(int id) {
        this.consolas.add(id);
    }

    public void addGenreID(int id) {
        this.generos.add(id);
    }

    public void addFoto(String foto) {
        this.imagen.add(foto);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getImagen() {
        return imagen;
    }

    public List<Integer> getGeneros() {
        return generos;
    }

    public List<Integer> getConsolas() {
        return consolas;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public int getStuidID() {
        return stuidID;
    }
}
