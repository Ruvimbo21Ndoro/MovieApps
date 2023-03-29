/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.movieapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class MovieDAO {

    public MovieDAO() {
    }

     
   
    private final String DB_URI = "jdbc:derby://localhost:1527/CinemaDB";
    private final String USERNAME = "administrator";
    private final String PASSWORD = "password";

  

    public void UpdateData(Movie movie) {
        
        Connection conn = null;
        Statement statement = null;
        int ok;


        try {
            conn = DriverManager.getConnection(DB_URI, USERNAME, PASSWORD);
            statement = conn.createStatement();
                     
            ok = statement.executeUpdate("UPDATE movie SET movie_title = '" + movie.getTitle() + "', movie_director = '" 
                    + movie.getDirectorName() + "', movie_genre = '" + movie.getGenre()+ "' WHERE movie_id = '" + movie.getMovie_id() + "'");

            if (ok > 0) {
                JOptionPane.showMessageDialog(null, "Successfully Movie updated" + movie.getMovie_id() );
            } else {
                JOptionPane.showMessageDialog(null, "An error occured");
            }

        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, "Error: " + sql.getMessage());
        } finally {

            try {
                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

            try {
                statement.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }
    
    
   public Movie getMovie(String movieID) {
       
       
        Movie movie = new Movie();

        String value = "";

        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;

        try {

            conn = DriverManager.getConnection(DB_URI, USERNAME, PASSWORD);
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM movie WHERE movie_id = '" + movieID + "'");

            if (result.next()) {

                movie.setTitle(result.getString(2));
                movie.setDirectorName(result.getString(3));
                movie.setGenre(result.getString(4));

            } else {
                System.out.println(" hello");
            }
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + sql.getMessage());
        } finally {

            try {
                conn.close();
                statement.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex);
            }

        }
        
        return movie;
        
   }
        
    
        
    public ArrayList<String> getMovie(){
        ArrayList<String> values = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;
        
          try {
            conn = DriverManager.getConnection(DB_URI, USERNAME, PASSWORD);
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT movie_id FROM movie ");
            
            while (result.next()) {
                values.add(result.getString(1));
            }
            
        } catch(SQLException sql) {
            JOptionPane.showMessageDialog(null, "Error: " + sql.getMessage());
        } finally {
            try{
                conn.close();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            try{
                statement.close();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
        
        return values;
    }

}
