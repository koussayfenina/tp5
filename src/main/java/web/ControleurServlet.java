package web;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;

import dao.IJoueurDao;
import dao.JoueurDaoImpl;
import metier.entities.Joueur;

@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
	IJoueurDao metier;
@Override
public void init() throws ServletException {
metier = new JoueurDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	String path=request.getServletPath();
if (path.equals("/index.do"))
{
	request.getRequestDispatcher("joueurs.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
	{
		String motCle=request.getParameter("motCle");
		JoueurModele model= new JoueurModele();
		model.setMotCle(motCle);
		List<Joueur> prods = metier.joueursParMC(motCle);
		model.setProduits(prods);
		request.setAttribute("model", model);
		request.getRequestDispatcher("joueurs.jsp").forward(request,response);
	}
else if (path.equals("/saisie.do") )
	{
	request.getRequestDispatcher("saisieProduit.jsp").forward(request,response);
	}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
	{
	 String nom=request.getParameter("nom");
	double prix = Double.parseDouble(request.getParameter("prix"));
	Joueur p = metier.save(new Joueur(nom,prix));
	request.setAttribute("joueur", p);
	request.getRequestDispatcher("confirmation.jsp").forward(request,response);
	}
else if (path.equals("/supprimer.do"))
	{
	 Long id= Long.parseLong(request.getParameter("id"));
	 metier.deleteJoueur(id);
	 response.sendRedirect("chercher.do?motCle=");
	}

	else if (path.equals("/editer.do")) {
		Long id = Long.parseLong(request.getParameter("id"));
		Joueur p = metier.getJoueur(id);
		request.setAttribute("joueur", p);
		request.getRequestDispatcher("editerProduit.jsp").forward(request, response);
		
	} else if (path.equals("/update.do")) {
		Long id = Long.parseLong(request.getParameter("id"));
		String nom = request.getParameter("nom");
		double prix = Double.parseDouble(request.getParameter("prix"));
		Joueur p = new Joueur();
		p.setIdJoueur(id);
		p.setNomJoueur(nom);
		p.setPrix(prix);
		metier.updateJoueur(p);
		request.setAttribute("joueur", p);
		request.getRequestDispatcher("confirmation.jsp").forward(request, response);
	}


	else
	{
	response.sendError(Response.SC_NOT_FOUND);
	}


}
@Override
protected void doPost(HttpServletRequest request, 
 HttpServletResponse response) throws ServletException, IOException {
	doGet(request,response);


}
}

