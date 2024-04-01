package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Joueur;

public class JoueurModele {
	private String motCle;
	List<Joueur> joueurs = new ArrayList<>();
	public String getMotCle() {
	return motCle;
	}
	public void setMotCle(String motCle) {
	this.motCle = motCle;
	}
	public List<Joueur> getJoueurs() {
	return joueurs;
	}
	public void setProduits(List<Joueur> joueurs) {
	this.joueurs = joueurs;
	}


}
