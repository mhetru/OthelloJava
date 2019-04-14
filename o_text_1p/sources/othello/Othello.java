package othello;

import othello.objet.*;
import othello.interf.*; // j'importe mes interfaces
import othello.util.*;
import java.util.*;


/**
 * Classe Jeu
 * Permet de cr�er un jeu
 */
public class Othello implements Game, Constantes { // notre classe pourra voir les constantes
    // attributs
    /** tab_cell qui contiendra les pions **/
    protected Grille g;
    protected boolean impossibilite_jouer_joueur_precedent = false;
    
    /**
     * Constructeur d'objets Jeu
     */
    public Othello() {
	g = new Grille(NBL,NBC);
    } // public Jeu() 
    
   
    
    /**
     * M�thode qui initialise les objets "Cells" dans la grille
     */
    public void init() {
	g.initialiser();
	g.initVoisins();
	g.initPions();
    } // public void initialiser()

    /**
     * M�thode qui permet d'afficher la grille
     */    
    public String afficheGrille() {
    	return g.affiche();
    }

    /**
     * M�thode qui permet d'afficher le nb de pions
     */    
    public String afficheNbPions() {
    	String tmp = "";
    	// System.out.println("Le joueur 1 [" + g.getPion(JOUEUR1) + "] a " + g.getNbPions(JOUEUR1) + " pions");
	tmp += "Le joueur 1 [" + g.getPion(JOUEUR1) + "] a " + g.getNbPions(JOUEUR1) + " pions\n";
    	//System.out.println("Le joueur 2 [" + g.getPion(JOUEUR2) + "] a " + g.getNbPions(JOUEUR2) + " pions");
	tmp += "Le joueur 2 [" + g.getPion(JOUEUR2) + "] a " + g.getNbPions(JOUEUR2) + " pions\n";
	return tmp;
    }
    
    /**
     * M�thode qui permet de rechercher les positions possibles � partir d'un joueur donn�
     * @param <code>int</code> valeur du joueur
     */    
    public void rechPositionsPossibles(int j) {
    	g.miseAjourPositionsPossibles(j);
    	
	// si la liste des positions possibles est vide alors c'est � l'autre joueur de jouer
	if (g.getListePositionsPossibles().size() == 0) {
	    System.out.println("Joueur " + g.getNbJoueur(j) + " : vous n'avez pas de choix possible!");
	    // les deux joueurs ne peuvent plus jouer ... fin de la partie ... detection du gagnant
	    if (impossibilite_jouer_joueur_precedent == true) {
		System.out.println("Les deux joueurs ne peuvent continuer... FIN DE LA PARTIE");
		this.declarerGagnant();
	    }
	    // g.alterneJoueur();
	    impossibilite_jouer_joueur_precedent = true;
	} else {
	    impossibilite_jouer_joueur_precedent = false;
	    
	    // TEMPORAIRE
	    Iterator it = g.getListePositionsPossibles().iterator();
	    Object ooo = null;
	    Position ppp = null;
	    System.out.println("Positions possibles pour le joueur " + g.getNbJoueur(j));
	    while(it.hasNext()) {
		ooo=it.next();
		ppp=(Position) ooo;
		System.out.print(ppp.toString() + " ");
	    }
	    System.out.println();
	    // TEMPORAIRE
	}
	
	// si Grille pleine => on arrete la partie
    	if ((g.getNbPions(JOUEUR1) + g.getNbPions(JOUEUR2)) == 64) {
	    System.out.println("Grille pleine !!!");
	    this.declarerGagnant();
    	}
	
    }
    
    /**
     * M�thode qui permet d'alterner les joueurs
     */    
    public void alterneJoueur() {
    	g.alterneJoueur();
    }

    /**
     * M�thode qui permet de faire jouer l'ordi
     * @return <code>String</code> position sous forme de caract�re
     */    
    public String faireJouerOrdi() {
    	Position best = alphaBeta(new Grille(g), JOUEUR2, PROFONDEUR, -1000, 1000, true);
	System.out.println("J'ai jou� en : " + best.toString());
    	return best.toString();
    }
    
    /**
     * M�thode de l'alphaBeta : permet de rechercher r�cursivement dans un arbre la meilleur position possible
     * @param <code>Grille</code> Grille initiale
     * @param <code>int</code> valeur du joueur
     * @param <code>int</code> valeur de la profondeur de recherche
     * @param <code>int</code> valeur du plancher beta
     * @param <code>int</code> valeur du plafond beta
     * @param <code>boolean</code> permet de distinguer l'appel alphaBeta MIN ou MAX (true pour Max et false pour MIN)
     * @return <code>Position</code> meilleur position
     */
    public Position alphaBeta(Grille gr, int joueur, int prof, int alpha, int beta, boolean isMax) {
	// on cr�e une position
    	Position pion = new Position();
    	
    	/**
    	 * si la profondeur est de 0
    	 * - c'est qu'on a d�j� parcouru tout l'arbre
    	 * donc on recherche la meilleure valeur et on retourne le pion
    	 */
    	if (prof == 0) {
	    int valeur = 0;
	    if (isMax == true) {
		valeur = gr.evaluationGrille(joueur); // on �value la grille si l'appel est MAX
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
    	
    	// je recherche toutes les positions possibles pour la grille initialise ou la grille copi�e (appell�e r�cursivement)
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
		Grille grillesuivante = new Grille(gr); // je cr�e une grille cop�e
		grillesuivante.retournePions(suivant, joueur); // et je retourne les pions pour chacune de ces valeurs
		Position resultat = alphaBeta(grillesuivante, -joueur, prof - 1, alpha, beta, !(isMax)); // et j'appelle mon alphaBeta dessus
		int nouvellevaleur = resultat.getValue(); // ensuite je r�cup�re sa valeur
		if (nouvellevaleur > alpha) { // si sa valeur est sup�rieur au plancher beta
		    alpha = nouvellevaleur; // on met � jour beta
		    bestX = suivant.getX(); // on r�cup�re les meilleures coordonn�es
		    bestY = suivant.getY();
		    if (alpha >= beta) { // par contre si alpha et beta sont identiques
			pion.setValue(beta); // je r�cup�re les coordonn�es du pion
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
     * M�thode qui traite nos saisies
     * @param <code>s</code> String qui correspond � la case choisie (exemple : a1 ou A1)
     * @param <code>int</code> valeur du joueur concern�
     */
    public void commandes(String s, int j) {
	// on d�clare les variables
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
	
	// si la longueur de la commande de l'utilisateur est sup�rieur � 2 ou �gale � 0 (touche entr�e)
	if (s.length() != 2) { // msg d'erreur
	    System.out.println("Erreur de saisie !!");
	    System.out.println("(Exemple de saisie: a1 ou A1)");
	    return;
	}
	
	// on d�coupe la commande de l'utilisateur
	colonne = s.substring(0,1);
	ligne = s.substring(1);
	
	// on transforme la string en char (colonne) 
	char[] tab = colonne.toCharArray();
	// on r�cup�re le premier caract�re de gauche
	char c = tab[0];
	colonne_entier = c - 'a';
	
	if ((c < 'a') || (c > ('a' + NBC))) {
	    System.out.println("Erreur : case incorrect : colonne invalide !!!");
	    return;
	}
	
	// on transforme le string en entier (ligne)
	ligne_entier = Integer.parseInt(ligne);
	// on d�cr�mente ligne_entier pour obtenir un indice correct pour notre tab_cell
	ligne_entier--;
	
	// si la ligne est hors de la matrice
	if ((ligne_entier > (NBL-1)) || (ligne_entier < 0)) {
	    System.out.println("Erreur: case incorrect : ligne invalide !!!");
	    return;
	}
	
	
	impossibilite_jouer_joueur_precedent = false;
	// si on est arriv� jusqu'ici : c'est que la commande est correct (= on est bien dans la matrice)
	// si il y a d�j� un pion sur la case qu'on a saisi ..
	if (g.getCellAt(new Position(ligne_entier,colonne_entier)).getEtat() == true) {
	    System.out.println("Erreur !! Il existe d�j� un pion sur cette case!");
	} else {
	    p_tmp = new Position(ligne_entier,colonne_entier);
	    if (g.getListePositionsPossibles().contains(p_tmp) == false) {
		System.out.println("Vous ne pouvez pas jouer en " + p_tmp.toString());
	    } else {
		// on pose le pion
		//g.getCellAt(new Position(ligne_entier,colonne_entier)).reverseChar(g.getTour());
		g.retournePions(p_tmp, j);
		// on alterne le joueur
		g.alterneJoueur();
		
	    } // if (liste_pp.contains(p_tmp) == false)
	    
	} // if (liste_pp.size() == 0)
	
    
    } // public void commandes(String s)

    /**
     * M�thode qui renvoit la valeur du joueur courant
     * @return <code>int</code> valeur du joueur courant
     */
    public void declarerGagnant() {
	this.afficheGrille();
	int nb_pions_j1 = g.getNbPions(JOUEUR1);
	int nb_pions_j2 = g.getNbPions(JOUEUR2);
	
	System.out.println("Le joueur1 a : " + nb_pions_j1);
	System.out.println("Le joueur2 a : " + nb_pions_j2);
	
	if (nb_pions_j1 == nb_pions_j2) {
	    System.out.println("Match nul !!!");
	} else {
	    if (nb_pions_j1 < nb_pions_j2) {
		System.out.println("Joueur2 a gagn� !!!");
	    } else {
		System.out.println("Joueur1 a gagn� !!!");
	    }
	}
	System.exit(0);
    }

    /**
     * M�thode qui renvoit la grille du jeu courant
     * @return <code>Grille</code> grille courante
     */    
    public Grille getGrille() {
	return g;
    }

    public int verifListe() {
	return getGrille().getTailleListe();
    }
    
    /**
     * M�thode qui renvoit la valeur du joueur courant
     * @return <code>int</code> valeur du joueur courant
     */
    public int getJoueurCourant() {
	return g.getTour();
    }

    /**
     * M�thode "main"
     * @param <code>String</code> tableau de param�tres au lancement
     */
    public static void main(String argv[]) {
	// message de bienvenue
	System.out.println();
	System.out.println("Bienvenue sur OTHELLO - (c) HETRU & MAQUET 2003");
	System.out.println();
	
	Othello o = new Othello();
	o.init(); // matrice remplie d'objets Cellule inactives (�tant � false)
	
	// on initialise la saisie du clavier
	String saisie = "";
      
	// boucle infinie
	while(true) {
	    
	    // on affiche le jeu � l'�cran
	    System.out.println(o.afficheGrille());
	    
	    
	    System.out.println("La grille du j1 vaut :" + o.getGrille().evaluationGrille(o.getJoueurCourant()));
	    System.out.println("La grille du j2 vaut :" + o.getGrille().evaluationGrille(-(o.getJoueurCourant())));
	    
	    
	    System.out.println(o.afficheNbPions());
	    
	    // on recherche les positions possibles
	    o.rechPositionsPossibles(o.getJoueurCourant());
	    
	    // si l'utilisateur peut pas jouer
	    if (o.verifListe() != 0) {
		
		if (o.getJoueurCourant() == JOUEUR2) {
		    System.out.println("C'est � l'ordi de JOUER !!!!");
		    saisie = o.faireJouerOrdi();
		    System.out.println("Appuyez sur [ENTER] pour continuer");
		    while(!Clavier.readString().equals("")) {
			System.out.println("Appuyez sur [ENTER] pour continuer");
		    }
		} else {
		    System.out.print("Saisissez votre case (q pour quitter):");
		    // on saisit la commande
		    saisie = Clavier.readString();
		}
		
		// on injecte notre commande (sous forme de String � notre jeu)
		o.commandes(saisie, o.getJoueurCourant());
	    } else {
      // sinon on alterne le joueur
		o.alterneJoueur();
	    }
	    
	} // while
      
	
	
    }
    
} // public class Othello implements Constantes



