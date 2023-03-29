/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.movieapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * s
 *
 * @author Dell
 */
public class MovieGUI extends JFrame implements ActionListener, ItemListener {

    // private String ;
    private JPanel panelNorth, panelCenter, panelSouth, panelWest;
    private JLabel lblHeading, lblTitle, lblDirector, lblGenre;
    private JLabel lblMovie;
    private JComboBox cboMovie;
    private JTextField txtMovie, txtTitle, txtDirector, txtGenre;
    private JButton btnUpdate, btnClear, btnExit;

    public MovieGUI() {
        super("Movies");

        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        panelWest = new JPanel();

        lblHeading = new JLabel("Movies");

        MovieDAO m = new MovieDAO();
        ArrayList<String> arr = m.getMovie();
        arr.add(0, "         -- No selection made --      ");

        String[] types = new String[arr.size()];

        for (int i = 0; i < types.length; i++) {
            types[i] = arr.get(i);
        }

        lblMovie = new JLabel("Movie Id:", SwingConstants.RIGHT);
        cboMovie = new JComboBox(types);

        lblTitle = new JLabel("TItle:", SwingConstants.RIGHT);
        txtTitle = new JTextField();

        lblDirector = new JLabel("Director Name:", SwingConstants.RIGHT);
        txtDirector = new JTextField();

        lblGenre = new JLabel("Genre:", SwingConstants.RIGHT);
        txtGenre = new JTextField();

        btnUpdate = new JButton("Update");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

    }

    public void setGUI() {
        panelCenter.setLayout(new GridLayout(4, 2));
        // panelSouth.setLayout(new GridLayout(1, 3));
        panelWest.setLayout(new GridLayout(3, 1));

        ImageIcon icon = new ImageIcon(new ImageIcon("user.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel(icon);
        panelNorth.add(picLabel);
        panelNorth.add(lblHeading);
        panelNorth.setBackground(Color.red);
        lblHeading.setForeground(Color.yellow);

        panelCenter.add(lblMovie);
        panelCenter.add(cboMovie);

        panelCenter.add(lblTitle);
        panelCenter.add(txtTitle);

        panelCenter.add(lblDirector);
        panelCenter.add(txtDirector);

        panelCenter.add(lblGenre);
        panelCenter.add(txtGenre);

        panelWest.add(btnUpdate);
        panelWest.add(btnClear);
        panelWest.add(btnExit);

        btnUpdate.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        btnUpdate.setEnabled(false);
        btnClear.setEnabled(false);
        cboMovie.addItemListener(this);

        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelWest, BorderLayout.WEST);

        setVisible(true);
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnExit) {
            System.exit(0);

        } else if (e.getSource() == btnClear) {
            resetForm();

        }
        if (e.getSource() == btnUpdate) {
            String movie_id = cboMovie.getSelectedItem().toString();
            String title = txtTitle.getText();
            String director = txtDirector.getText();
            String genre = txtGenre.getText();

            Movie movie = new Movie(movie_id, title, director, genre);
            MovieDAO m = new MovieDAO();
            m.UpdateData(movie);
            resetForm();

        }
        if (e.getSource() == cboMovie) {

        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println(cboMovie.getSelectedItem());

            MovieDAO m = new MovieDAO();

            Movie s = m.getMovie(cboMovie.getSelectedItem().toString());

            txtTitle.setText(s.getTitle());
            txtDirector.setText(s.getDirectorName());
            txtGenre.setText(s.getGenre());

            btnUpdate.setEnabled(true);
            btnClear.setEnabled(true);

        }
    }

    public void resetForm() {
        cboMovie.setSelectedIndex(0);
        txtTitle.setText(" ");
        txtDirector.setText(" ");
        txtGenre.setText(" ");

        btnUpdate.setEnabled(false);
        btnClear.setEnabled(false);

    }

}

//if(e.getStateChange() == ItemEvent.SELECTED){
//
//      if(!cboStudentNumbers.getSelectedItem().equals("---No selection made---")) {
//
//        if (e.getSource() == cboStudentNumbers) {

