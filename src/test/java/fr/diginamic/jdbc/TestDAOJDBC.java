package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDAO;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc2;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.ArrayList;
import java.util.List;

public class TestDAOJDBC {
    public static void main(String[] args){
        Fournisseur f1 = new Fournisseur("France de matériaux");
        String nouveauNom = "France matériaux";
        FournisseurDAO fdao = new FournisseurDaoJdbc2();
        fdao.insert(f1);
        extraire(fdao);
        fdao.update(f1.getNom(), nouveauNom);
        extraire(fdao);
        f1.setNom(nouveauNom);
        fdao.delete(f1);
        extraire(fdao);


    }
    private static void extraire(FournisseurDAO fdao){
        List<Fournisseur> listFournisseurs = new ArrayList<>();
        listFournisseurs = fdao.extraire();
        for(Fournisseur fournisseur :  listFournisseurs){
            System.out.println(fournisseur.toString());
        }
        System.out.println();
    }
}
