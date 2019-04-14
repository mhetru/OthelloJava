package othello.objet;

import othello.objet.*;
import othello.util.*;
import othello.interf.*;
 
public class Partie implements Constantes {
    private Game jeu_courant;
    private String saisie = "";
    
    public Partie(Game g) {
	jeu_courant = g;
    }
    
    public void launch() {
	jeu_courant.init(); // matrice remplie d'objets Cellule inactives (étant à false)
	
	// on initialise la saisie du clavier
	String saisie = "";
	
	// boucle infinie
	while(true) {
	    
	    // on affiche le jeu à l'écran
	    jeu_courant.afficheGrille();
	    
	    if (jeu_courant instanceof Ia) {
		System.out.println("La grille du j1 vaut :" + jeu_courant.getGrille().evaluationGrille(jeu_courant.getJoueurCourant()));
		System.out.println("La grille du j2 vaut :" + jeu_courant.getGrille().evaluationGrille(-(jeu_courant.getJoueurCourant())));
	    }
	    
	    jeu_courant.afficheNbPions();
	    
	    // on recherche les positions possibles
	    jeu_courant.rechPositionsPossibles(jeu_courant.getGrille().getTour());

	    // si l'utilisateur peut pas jouer
	    if (jeu_courant.getGrille().getListePositionsPossibles().size() != 0) {

		if ( (jeu_courant.getJoueurCourant() == JOUEUR2) && (jeu_courant instanceof Ia) ) {
		    System.out.println("C'est à l'ordi de JOUER !!!!");
		    saisie = jeu_courant.faireJouerOrdi();
		    System.out.println("Appuyez sur [ENTER] pour continuer");
		    while(!Clavier.readString().equals("")) {
			System.out.println("Appuyez sur [ENTER] pour continuer");
		    }
		} else {
		    System.out.print("Saisissez votre case (q pour quitter):");
		    // on saisit la commande
		    saisie = Clavier.readString();
		}
	    
		// on injecte notre commande (sous forme de String à notre jeu)
		jeu_courant.commandes(saisie, jeu_courant.getGrille().getTour());
	    } else {
		// sinon on alterne le joueur
		jeu_courant.alterneJoueur();
	    }
	    
	} // while
    }
}
