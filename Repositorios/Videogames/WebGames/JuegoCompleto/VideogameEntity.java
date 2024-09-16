package proyecto.sergio.demo.Repositorios.Videogames.WebGames.JuegoCompleto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideogameEntity {
    private int id;
    private String titulo;
    private String descripcion;
    private List<String> imagen;
    private String generos;
    private String consolas;
    private HashMap<Integer, String> roms;
    private String publisher;
    private String studio;
    private List<String> manuales;
    private List<String> guias;


    public VideogameEntity(int id, String titulo, String descripcion, String generos, String consolas, String publisher, String studio) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.generos = generos;
        this.consolas = consolas;
        this.publisher = publisher;
        this.studio = studio;
        this.manuales = new ArrayList<>();
        this.guias = new ArrayList<>();
        this.imagen = new ArrayList<>();
        this.roms = new HashMap<>();
    }

    public void addManual(String manual) {
        this.manuales.add(manual);
    }

    public void addGuia(String guia) {
        this.guias.add(guia);
    }

    public void addRoms(int id, String rom) {
        this.roms.put(id, rom);
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

    public String getGeneros() {
        return generos;
    }

    public String getConsolas() {
        return consolas;
    }

    public HashMap<Integer, String> getRoms() {
        return roms;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getStudio() {
        return studio;
    }

    public List<String> getManuales() {
        return manuales;
    }

    public List<String> getGuias() {
        return guias;
    }
}
