package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurDaoJdbc implements FournisseurDAO{
    private String url;
    private String user;
    private Connection conn;

    public FournisseurDaoJdbc() {
        ResourceBundle config = ResourceBundle.getBundle("database");

        this.url = config.getString("db.url");
        this.user = config.getString("db.user");
        try{
        this.conn = DriverManager.getConnection(url, user, "");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> result = new ArrayList<>();
        String request = "SELECT * FROM fournisseur";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            if(rs != null) {
                while (rs.next()) {
                    result.add(new Fournisseur(rs.getInt("id"), rs.getString("nom")));
                }
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO fournisseur(nom) VALUES ('").append(fournisseur.getNom()).append("');");
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sb.toString());
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE fournisseur SET nom = '");
        sb.append(nouveauNom);
        sb.append("' WHERE nom = '");
        sb.append(ancienNom);
        sb.append("';");
        System.out.println(sb.toString());
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sb.toString());
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        boolean result = false;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM fournisseur WHERE nom = '");
        sb.append(fournisseur.getNom()).append("';");
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sb.toString())>0;
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
