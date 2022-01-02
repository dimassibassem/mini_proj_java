package org.bassem;

import java.util.Objects;

public class Personne {
    private Integer id;
    private String nom;
    private String genre;

    public Personne(Integer id, String nom, String genre) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
    }

    public Personne(String nom, String genre) {
        this.nom = nom;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(id, personne.id) && Objects.equals(nom, personne.nom) && Objects.equals(genre, personne.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, genre);
    }

    @Override
    public String toString() {
        return "Personne{id=%d, nom='%s', genre='%s'}".formatted(id, nom, genre);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
