/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.movieapp;

/**
 *
 * @author Dell
 */
public class Movie {

    private String movie_id;
    private String title;
    private String directorName;
    private String genre;

    public Movie() {
    }

    public Movie(String movie_id, String title, String directorName, String genre) {
        this.movie_id = movie_id;
        this.title = title;
        this.directorName = directorName;
        this.genre = genre;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
