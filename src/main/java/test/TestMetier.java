package test;

import java.util.List;

import dao.JoueurDaoImpl;
import metier.entities.Joueur;
public class TestMetier {
public static void main(String[] args) {
	JoueurDaoImpl pdao= new JoueurDaoImpl();
	Joueur prod= pdao.save(new Joueur("mehrez h",10000));
	System.out.println(prod);
	List<Joueur> prods =pdao.joueursParMC("h");
	for (Joueur p : prods)
	System.out.println(p);
}
}
