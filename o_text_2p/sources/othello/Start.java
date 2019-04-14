package othello;

import othello.interf.*;
import othello.objet.*;
import othello.util.*;

/**
 * Classe de démarrage
 * permet de lancer le jeu Othello grace à un menu
 *
 * @author Mathieu HETRU & Frédéric MAQUET
 * @version 1.0,21 Mars 2003
 */
public class Start {
	
    /** partie courante */
    private static Partie p;

	/** String de saisie */
    private static String saisie = "";
    
    /** Entier de saisie */
    private static int entier_saisie = 0;

    /**
     * Méthode "main"
     * @param <code>String</code> tableau de paramètres au lancement
     */
    public static void main(String argv[]) {
	// message de bienvenue
	System.out.println();
	System.out.println("Bienvenue sur OTHELLO - (c) HETRU & MAQUET 2003");
	System.out.println();
	
	// boucle infinie
	while(true) {
	    // affichage du menu
	    System.out.println();
	    System.out.println("Choisissez le type de jeu :");
	    System.out.println("1 : 1 joueur (1 joueur humain contre l'ordinateur)");
	    System.out.println("2 : 2 joueurs(2 joueurs humain)");
	    System.out.println("3 : Quitter");
	    System.out.print("?");
	    
	    try {
		    // l'utilisateur saisit son choix (parmi ceux de la liste : 1, 2 ou 3)
		    saisie = Clavier.readString();
		    
		    // on convertit la String en Int pour le switch juste après ..
		    entier_saisie = Integer.parseInt(saisie);
		    
		    // on découpe les choix possibles ..
		    switch(entier_saisie) {
		    case 1:
			p = new Partie(new Ia());
			p.launch();
			break;
		    case 2:
			p = new Partie(new Jeu());
			p.launch();
			break;
		    case 3:
			System.out.println("Have Fun !");
			System.exit(0);
			break;
		    default:
			System.out.println("Choix incorrect !");
			break;
		    }
		  } catch(Exception e) {
		  	System.out.println("Erreur : saisir un entier !");
		  }
	}
	
    }
}
