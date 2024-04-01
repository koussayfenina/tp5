package dao;

import java.util.List;

import metier.entities.Joueur;

public interface IJoueurDao {
	public Joueur save(Joueur j);
	public List<Joueur> joueursParMC(String mc);
	public Joueur getJoueur(Long id);
	public Joueur updateJoueur(Joueur p);
	public void deleteJoueur(Long id);

}
