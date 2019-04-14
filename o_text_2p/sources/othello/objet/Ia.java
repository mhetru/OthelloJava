package othello.objet;

import othello.interf.*;
import othello.objet.*;
import java.util.*;



public class Ia implements Game, Constantes{
	
	protected Grille g_courante;
	protected boolean impossibilite_jouer_joueur_precedent = false;
	
	/**
     * Constructeur d'objets Jeu
     */
    public Ia() {
		g_courante = new Grille(NBL,NBC);
		g_courante.initialiser();
		
    } // public Jeu()
		
		
	/**
     * Méthode qui initialise les objets "Cells" dans la grille
     */
    public void init() {
		g_courante.initialiser();
		g_courante.initVoisins();
		g_courante.initPions();
    } // public void initialiser()
    
    /**
     * Méthode qui permet d'afficher la grille
     */
    public void afficheGrille() {
    	g_courante.affiche();
    }
    
    /**
     * Méthode qui permet d'afficher le nb de pions
     */
    public void afficheNbPions() {
    	System.out.println("Le joueur 1 [" + g_courante.getPion(JOUEUR1) + "] a " + g_courante.getNbPions(JOUEUR1) + " pions");
    	System.out.println("Le joueur 2 [" + g_courante.getPion(JOUEUR2) + "] a " + g_courante.getNbPions(JOUEUR2) + " pions");
    }
    
    /**
     * Méthode qui permet de rechercher les positions possibles à partir d'un joueur donné
     * @param <code>int</code> valeur du joueur
     */
    public void rechPositionsPossibles(int j) {
    	g_courante.miseAjourPositionsPossibles(j);
    	
    	// si la liste des positions possibles est vide alors c'est à l'autre joueur de jouer
	if (g_courante.getListePositionsPossibles().size() == 0) {
	    System.out.println("Joueur " + g_courante.getNbJoueur(j) + " : vous n'avez pas de choix possible!");
	    // les deux joueurs ne peuvent plus jouer ... fin de la partie ... detection du gagnant
	    if (impossibilite_jouer_joueur_precedent == true) {
		System.out.println("Les deux joueurs ne peuvent continuer... FIN DE LA PARTIE");
		this.declarerGagnant();
	    }
	    // g_courante.alterneJoueur();
	    impossibilite_jouer_joueur_precedent = true;
	} else {
	    impossibilite_jouer_joueur_precedent = false;
	    
	    // TEMPORAIRE
	    Iterator it = g_courante.getListePositionsPossibles().iterator();
	    Object ooo = null;
	    Position ppp = null;
	    System.out.println("Positions possibles pour le joueur " + g_courante.getNbJoueur(j));
	    while(it.hasNext()) {
		ooo=it.next();
		ppp=(Position) ooo;
		System.out.print(ppp.toString() + " ");
	    }
	    System.out.println();
	    // TEMPORAIRE
	}
	
	// si Grille pleine => on arrete la partie
    	if ((g_courante.getNbPions(JOUEUR1) + g_courante.getNbPions(JOUEUR2)) == 64) {
	    System.out.println("Grille pleine !!!");
	    this.declarerGagnant();
    	}
    }
    
    /**
     * Méthode qui permet d'alterner les joueurs
     */
    public void alterneJoueur() {
    	g_courante.alterneJoueur();
    }
    
    /**
     * Méthode qui permet de faire jouer l'ordi
     * @return <code>String</code> position sous forme de caractère
     */
    public String faireJouerOrdi() {
    	Position best = alphaBeta(new Grille(g_courante), JOUEUR2, PROFONDEUR, -1000, 1000, true);
		System.out.println("J'ai joué en : " + best.toString());
    	return best.toString();
    }
    
    /**
     * Méthode de l'alphaBeta : permet de rechercher récursivement dans un arbre la meilleur position possible
     * @param <code>Grille</code> Grille initiale
     * @param <code>int</code> valeur du joueur
     * @param <code>int</code> valeur de la profondeur de recherche
     * @param <code>int</code> valeur du plancher beta
     * @param <code>int</code> valeur du plafond beta
     * @param <code>boolean</code> permet de distinguer l'appel alphaBeta MIN ou MAX (true pour Max et false pour MIN)
     * @return <code>Position</code> meilleur position
     */
    public Position alphaBeta(Grille gr, int joueur, int prof, int alpha, int beta, boolean isMax) {
    	// on crée une position
    	Position pion = new Position();
    	
    	/**
    	 * si la profondeur est de 0
    	 * - c'est qu'on a déjà parcouru tout l'arbre
    	 * donc on recherche la meilleure valeur et on retourne le pion
    	 */
    	if (prof == 0) {
	    	int valeur = 0;
	    	if (isMax == true) {
				valeur = gr.evaluationGrille(joueur); // on évalue la grille si l'appel est MAX
	    	} else {
				valeur = gr.evaluationGrille(-joueur);// pour MIN
	    	}
	    	pion.setValue(valeur);// on stocke la valeur dans le pion ..
	    	return pion; // avant de le retourner
    	}

    	//if (isMax == true) {
		gr.miseAjourPositionsPossibles(joueur);
		//} else {
		//gr.miseAjourPositionsPossibles(-joueur);
		//}
    	
    	// je recherche toutes les positions possibles pour la grille initialise ou la grille copiée (appellée récursivement)
    	LinkedList successeurs  = gr.getListePositionsPossibles();
    	
    	if (successeurs.size() == 0) {
	    int valeur = 0;
	    if (isMax == true) {
		valeur = gr.evaluationGrille(joueur);
	    } else {
		valeur = gr.evaluationGrille(-joueur);
	    }
	    // pion.setValue(valeur);
	    return pion;
    	}
    	
    	int bestX = 0;
    	int bestY = 0;
    	
    	if (isMax == true) { // si la profondeur est MAX
		    for(Iterator it = successeurs.iterator(); it.hasNext(); ) { // je scanne toutes les positions pour cette profondeur
				Position suivant = (Position) it.next(); // pour chaque position
				Grille grillesuivante = new Grille(gr); // je crée une grille copée
				grillesuivante.retournePions(suivant, joueur); // et je retourne les pions pour chacune de ces valeurs
				Position resultat = alphaBeta(grillesuivante, -joueur, prof - 1, alpha, beta, !(isMax)); // et j'appelle mon alphaBeta dessus
				int nouvellevaleur = resultat.getValue(); // ensuite je récupère sa valeur
				if (nouvellevaleur > alpha) { // si sa valeur est supérieur au plancher beta
				    alpha = nouvellevaleur; // on met à jour beta
				    bestX = suivant.getX(); // on récupère les meilleures coordonnées
				    bestY = suivant.getY();
				    if (alpha >= beta) { // par contre si alpha et beta sont identiques
						pion.setValue(beta); // je récupère les coordonnées du pion
						pion.setX(bestX);
						pion.setY(bestY);
						return pion;
				    }
				}
		    }
		    pion.setValue(alpha);
		    pion.setY(bestY);
		    pion.setX(bestX);
		    return pion;
    	} else { // PAREIL POUR MIN
		    for(Iterator it = successeurs.iterator(); it.hasNext(); ) {
				Position suivant = (Position) it.next();
				Grille grillesuivante = new Grille(gr);
				grillesuivante.retournePions(suivant, joueur);
				Position resultat = alphaBeta(grillesuivante, -joueur, prof - 1, alpha, beta, !(isMax));
				int nouvellevaleur = resultat.getValue();
				if (nouvellevaleur < beta) {
				    beta = nouvellevaleur;
				    bestX = suivant.getX();
				    bestY = suivant.getY();
				    if (alpha >= beta) {
					pion.setValue(alpha);
					pion.setX(bestX);
					pion.setY(bestY);
					return pion;
				    }
				}
		    }
		    pion.setValue(beta);
	    	pion.setX(bestX);
	    	pion.setY(bestY);
	    	return pion;
    	}
    }
    
    /**
     * Méthode qui traite nos saisies
     * @param <code>s</code> String qui correspond à la case choisie (exemple : a1 ou A1)
     * @param <code>int</code> valeur du joueur concerné
     */
    public void commandes(String s, int j) {
	// on déclare les variables
	String colonne = null;
	String ligne = null;
	int ligne_entier = 0;
	int colonne_entier = 0;
	Object ob = null; // objet temporaire
	Position p_tmp = null;
	
	// on met en minuscules la commande de l'utilisateur
	s = s.toLowerCase();
	
	// si l'utilisateur a saisit la lettre "q" ou "Q"
	if (s.equals("q")) {
	    // alors le programme quitte
	    System.exit(0);
	}
	
	// si la longueur de la commande de l'utilisateur est supérieur à 2 ou égale à 0 (touche entrée)
	if (s.length() != 2) { // msg d'erreur
	    System.out.println("Erreur de saisie !!");
	    System.out.println("(Exemple de saisie: a1 ou A1)");
	    return;
	}
	
	// on découpe la commande de l'utilisateur
	colonne = s.substring(0,1);
	ligne = s.substring(1);
	
	// on transforme la string en char (colonne) 
	char[] tab = colonne.toCharArray();
	// on récupère le premier caractère de gauche
	char c = tab[0];
	colonne_entier = c - 'a';
	
	if ((c < 'a') || (c > ('a' + NBC))) {
	    System.out.println("Erreur : case incorrect : colonne invalide !!!");
	    return;
	}
	
	// on transforme le string en entier (ligne)
	ligne_entier = Integer.parseInt(ligne);
	// on décrémente ligne_entier pour obtenir un indice correct pour notre tab_cell
	ligne_entier--;
	
	// si la ligne est hors de la matrice
	if ((ligne_entier > (NBL-1)) || (ligne_entier < 0)) {
	    System.out.println("Erreur: case incorrect : ligne invalide !!!");
	    return;
	}
	
	
	
	impossibilite_jouer_joueur_precedent = false;
	// si on est arrivé jusqu'ici : c'est que la commande est correct (= on est bien dans la matrice)
	// si il y a déjà un pion sur la case qu'on a saisi ..
	if (g_courante.getCellAt(new Position(ligne_entier,colonne_entier)).getEtat() == true) {
	    System.out.println("Erreur !! Il existe déjà un pion sur cette case!");
	} else {
	    p_tmp = new Position(ligne_entier,colonne_entier);
	    if (g_courante.getListePositionsPossibles().contains(p_tmp) == false) {
		System.out.println("Vous ne pouvez pas jouer en " + p_tmp.toString());
	    } else {
		// on pose le pion
		// g_courante.getCellAt(new Position(ligne_entier,colonne_entier)).reverseChar(g_courante.getTour());
		g_courante.retournePions(p_tmp, j);
		// on alterne le joueur
		g_courante.alterneJoueur();
		    
	    } // if (liste_pp.contains(p_tmp) == false)
	    
	} // if (liste_pp.size() == 0)
	
    } // public void commandes(String s)
    
    /**
     * Méthode qui renvoit la grille du jeu courant
     * @return <code>Grille</code> grille courante
     */
    public Grille getGrille() {
	return g_courante;
    }
    
        /**
     * Méthode qui renvoit la valeur du joueur courant
     * @return <code>int</code> valeur du joueur courant
     */
    public void declarerGagnant() {
	this.afficheGrille();
	int nb_pions_j1 = g_courante.getNbPions(JOUEUR1);
	int nb_pions_j2 = g_courante.getNbPions(JOUEUR2);
	
	System.out.println("Le joueur1 a : " + nb_pions_j1);
	System.out.println("Le joueur2 a : " + nb_pions_j2);
	
	if (nb_pions_j1 == nb_pions_j2) {
	    System.out.println("Match nul !!!");
	} else {
	    if (nb_pions_j1 < nb_pions_j2) {
		System.out.println("Joueur2 a gagné !!!");
	    } else {
		System.out.println("Joueur1 a gagné !!!");
	    }
	}
	
	System.exit(0);
    }
    
    /**
     * Méthode qui renvoit la valeur du joueur courant
     * @return <code>int</code> valeur du joueur courant
     */
    public int getJoueurCourant() {
	return g_courante.getTour();
    }
    
}