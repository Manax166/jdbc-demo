package fr.diginamic.jdbc;

public class TestDelete {
    public static void main(String[] args){
        String request = "";
        request = "DELETE FROM fournisseur WHERE nom = 'La Maison des Peintures';";
        JDBCCall.executeRequest(request);
        request = "SELECT * FROM fournisseur;";
        JDBCCall.executeRequest(request);
    }
}
