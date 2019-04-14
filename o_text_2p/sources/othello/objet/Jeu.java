package othello.objet;

import othello.objet.*;
import othello.interf.*; // j'importe mes interfaces
import java.util.*;


/**
 * Classe Jeu
 * Permet de cr�er un jeu
 */
public class Jeu implements Game, Constantes { // notre classe pourra voir les constantes
    // attributs
    /** tab_cell qui contiendra les pions **/
    protected Grille g;
    protected boolean impossibilite_jouer_joueur_precedent = false;
    
    /**
     * Constructeur d'objets Jeu
     */
    public Jeu() {
	g = new Grille(NBL,NBC);
    } // public Jeu() 
    
    public Jeu(Jeu source){
    	g = new Grille(source.getGrille());
    }	
    
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
    public void afficheGrille() {
    	g.affiche();
    }

    /**
     * M�thode qui permet d'afficher le nb de pions
     */    
    public void afficheNbPions() {
    	System.out.println("Le joueur 1 [" + g.getPion(JOUEUR1) + "] a " + g.getNbPions(JOUEUR1) + " pions");
    	System.out.println("Le joueur 2 [" + g.getPion(JOUEUR2) + "] a " + g.getNbPions(JOUEUR2) + " pions");
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
    	return "";
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
	return null;
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
    
    /**
     * M�thode qui renvoit la valeur du joueur courant
     * @return <code>int</code> valeur du joueur courant
     */
    public int getJoueurCourant() {
	return g.getTour();
    }
    
} // public class Jeu implements Constantes
