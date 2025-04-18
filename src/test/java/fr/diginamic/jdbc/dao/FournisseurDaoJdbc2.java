package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurDaoJdbc2 implements FournisseurDAO {

    private String url;
    private String user;
    private Connection conn;

    public FournisseurDaoJdbc2() {
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
            PreparedStatement stmt = conn.prepareStatement(request);
            ResultSet rs = stmt.executeQuery();
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
        sb.append("INSERT INTO fournisseur(nom) VALUES (?);");
        try {
            PreparedStatement stmt = conn.prepareStatement(sb.toString());
            stmt.setString(1,fournisseur.getNom());
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE fournisseur SET nom = ? WHERE nom = ?;");
        try {
            PreparedStatement stmt = conn.prepareStatement(sb.toString());
            stmt.setString(1, ancienNom);
            stmt.setString(2, nouveauNom);
            result = stmt.executeUpdate();
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
        sb.append("DELETE FROM fournisseur WHERE nom = ?;");
        try {
            PreparedStatement stmt = conn.prepareStatement(sb.toString());
            stmt.setString(1, fournisseur.getNom());
            result = stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

