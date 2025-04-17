package fr.diginamic.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class TestInsertion {
    public static void main(String[] args){
        String request = "";
        request = "INSERT INTO fournisseur(nom) VALUES (\"La Maison de la peinture\");";
        JDBCCall.executeRequest(request);
        request = "SELECT * FROM fournisseur;";
        JDBCCall.executeRequest(request);
    }
}
