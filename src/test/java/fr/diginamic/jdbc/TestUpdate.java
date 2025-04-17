package fr.diginamic.jdbc;

public class TestUpdate {
    public static void main(String[] args){
        String request = "";
        request = "UPDATE fournisseur SET nom = 'La Maison des Peintures' WHERE nom = 'La Maison de la peinture';";
        JDBCCall.executeRequest(request);
        request = "SELECT * FROM fournisseur;";
        JDBCCall.executeRequest(request);
    }
}
