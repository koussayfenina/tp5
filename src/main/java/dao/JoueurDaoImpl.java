package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import metier.entities.Joueur;
import util.JpaUtil;
public class JoueurDaoImpl implements IJoueurDao {
private EntityManager entityManager=JpaUtil.getEntityManager("TP5");
		@Override
		public Joueur save(Joueur p) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(p);
		tx.commit();
		return p;
		}

		@Override
		public List<Joueur> joueursParMC(String mc) {
			List<Joueur> prods = entityManager.createQuery("select p from joueur p where p.nomJoueur like :mc").setParameter("mc", "%"+mc+"%").getResultList();
				return prods;
		}
		
		@Override
		public Joueur getJoueur(Long id) {
			return entityManager.find(Joueur.class, id);
		}
		@Override
		public Joueur updateJoueur(Joueur p) {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.merge(p);
			tx.commit();
			return p;
		}
		@Override
		public void deleteJoueur(Long id) {
			Joueur produit = entityManager.find(Joueur.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(produit);
			entityManager.getTransaction().commit();
			
		}
}