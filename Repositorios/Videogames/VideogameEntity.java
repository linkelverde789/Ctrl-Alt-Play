package proyecto.sergio.demo.Repositorios.Videogames;


import java.util.ArrayList;
import java.util.List;

public class VideogameEntity {
    private int ID;
    private int studioID;
    private int publisherID;
    private String title;
    private String description;
    private List<String> images;

    public VideogameEntity(int ID, int studioID, int publisherID, String title, String description) {
        this.ID = ID;
        this.studioID = studioID;
        this.publisherID = publisherID;
        this.title = title;
        this.description = description;
        this.images = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public int getStudioID() {
        return studioID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return new ArrayList<>(this.images);
    }

    public void addFoto(String nombreFoto) {
        this.images.add(nombreFoto);
    }

    @Override
    public String toString() {
        return "ID: " + ID +
                ", ID Estudio: " + studioID +
                ", ID Distribuidora: " + publisherID +
                ", Título: " + title +
                ", Descripción: " + description;
    }
}
