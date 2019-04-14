package othello.objet;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import othello.objet.*;
import othello.interf.*;
import othello.util.*;



/**
 * Classe Jeu
 * Permet de cr�er un jeu
 */
public class Jeu implements Constantes { // notre classe pourra voir les constantes
    
    // attributs
    /** la fenetre en elle-m�me*/
    public JFrame f = new JFrame("Othello (MAQUET & HETRU)");
    
    /** tab_cell qui contiendra les pions **/
    public Grille g;
    public boolean impossibilite_jouer_joueur_precedent = false;
    public int mode;
    
    /** la barre d'�tat ou status & infos */
    public JLabel etat;
	public JLabel infos;
	
	/** profondeur par d�faut de l'intelligence artificielle */
    public int profondeur = 3;
	
	/** compteur de partie */
	public int nbparties = 0;
	public int nbpartiestotal = 0;
	
	/** compteur de coups */
	public int nbcoups = 0;
	
	/** t�moin d'initialisation */
	private boolean initialise = false;
	
	/** les joueurs */
	public Joueur joueur1;
	public Joueur joueur2;
    
    /**
     * Constructeur par d�faut
     * (vide bien-s�r car il est utilis� pour l'h�ritage => obligation de faire un super..
     */
    public Jeu() {}
    
    /**
     * Constructeur d'un jeu
     * @param <code>int</code> entier repr�sentant le mode du jeu (1=1 joueur, 2=2 joueurs)
     */
    public Jeu(int m) {	
		g = new Grille(SIZE, SIZE);
		mode = m;
    } // public Jeu(int m)
    
    /**
     * M�thode NouvellePartie
     * lancant une nouvelle partie
     */
    public void nouvellePartie() {
    	nbcoups = 0;
    	if ((nbparties+1) > nbpartiestotal) {
    		initialise = false;
    	} else {
    		nbparties++;
    		// lance une nouvelle partie
    		init();
    		// alterneJoueur();
    	}
    }
    
    /**
     * M�thode initial
     * @param <code>int</code>1=mode 1joueur, 2=mode 2joueurs
     */
    public void initial(int m) {
    	g = new Grille(SIZE, SIZE);
    	mode = m;
    	nbparties = 1;
    	this.init();
    } 
    
        
    /**
     * M�thode qui initialise les objets "Cells" dans la grille
     */
    public void init() {
		g.initialiser();
		g.initVoisins();
		g.initPions();
		initialise = true;
    } // public void initialiser()

	/**
	 * M�thode qui renvoit la valeur de la variable initialise
	 * @param <code>boolean</code> true ou false
	 */
	public boolean getInit() {
		return initialise;
	}

    /**
     * M�thode qui permet d'afficher la grille
     */    
    public void afficheGrille() {
    	if (this.getInit()) {
    		g.affiche();
    	}
    }

    /**
     * M�thode qui permet d'afficher le nb de pions
     */    
    public void afficheNbPions() {
    	if (this.getJoueurCourant().equals(joueur1)) {
    		infos.setText(" >>" + joueur1.getName() + " [" + g.getNbPions(joueur1.getValue()) + " pions]         " + joueur2.getName() + " [" + g.getNbPions(joueur2.getValue()) + " pions]");
    	} else {
    		infos.setText("  " + joueur1.getName() + " [" + g.getNbPions(joueur1.getValue()) + " pions]       >>" + joueur2.getName() + " [" + g.getNbPions(joueur2.getValue()) + " pions]");
    	}
    	/*System.out.println("Le joueur 1 [" + g.getPion(JOUEUR1) + "] a " + g.getNbPions(JOUEUR1) + " pions");
    	System.out.println("Le joueur 2 [" + g.getPion(JOUEUR2) + "] a " + g.getNbPions(JOUEUR2) + " pions");*/
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
				// System.out.println("Les deux joueurs ne peuvent continuer... FIN DE LA PARTIE");
				this.declarerGagnant();
		    }
		    // g.alterneJoueur();
		    impossibilite_jouer_joueur_precedent = true;
		} else {
		    impossibilite_jouer_joueur_precedent = false;
		    
		    // TEMPORAIRE
		    /*Iterator it = g.getListePositionsPossibles().iterator();
		    Object ooo = null;
		    Position ppp = null;
		    System.out.println("Positions possibles pour le joueur " + g.getNbJoueur(j));
		    while(it.hasNext()) {
				ooo=it.next();
				ppp=(Position) ooo;
				System.out.print(ppp.toString() + " ");
		    }
		    System.out.println();*/
		    // TEMPORAIRE
		}
		
		// si Grille pleine => on arrete la partie
    	if ((g.getNbPions(JOUEUR1) + g.getNbPions(JOUEUR2)) == 64) {
		    // System.out.println("Grille pleine !!!");
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
    	Position best = alphaBeta(new Grille(g), this.getJoueurCourant().getValue(), profondeur, -1000, 1000, true);
		// System.out.println("J'ai jou� en : " + best.toString());
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
    	
		gr.miseAjourPositionsPossibles(joueur);
    	
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
		    // System.out.println("Erreur de saisie !!");
		    // System.out.println("(Exemple de saisie: a1 ou A1)");
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
		
		if ((c < 'a') || (c > ('a' + SIZE))) {
		    // System.out.println("Erreur : case incorrect : colonne invalide !!!");
		    return;
		}
		
		// on transforme le string en entier (ligne)
		ligne_entier = Integer.parseInt(ligne);
		// on d�cr�mente ligne_entier pour obtenir un indice correct pour notre tab_cell
		ligne_entier--;
		
		// si la ligne est hors de la matrice
		if ((ligne_entier > (SIZE-1)) || (ligne_entier < 0)) {
		    // System.out.println("Erreur: case incorrect : ligne invalide !!!");
		    return;
		}
		
		
		impossibilite_jouer_joueur_precedent = false;
		// si on est arriv� jusqu'ici : c'est que la commande est correct (= on est bien dans la matrice)
		// si il y a d�j� un pion sur la case qu'on a saisi ..
		if (g.getCellAt(new Position(ligne_entier,colonne_entier)).getEtat() == true) {
		    // System.out.println("Erreur !! Il existe d�j� un pion sur cette case!");
			Msgbox.affMsg(f, "Vous ne pouvez pas jouer ici !!");
		} else {
		    p_tmp = new Position(ligne_entier,colonne_entier);
		    if (g.getListePositionsPossibles().contains(p_tmp) == false) {
				Msgbox.affMsg(f, "Vous ne pouvez pas jouer ici !!");
				// System.out.println("Vous ne pouvez pas jouer en " + p_tmp.toString());
		    } else {
				// on pose le pion
				//g.getCellAt(new Position(ligne_entier,colonne_entier)).reverseChar(g.getTour());
				g.retournePions(p_tmp, j);
				
				// on incr�mente le nb de coups
				nbcoups++;
				
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
		
		// System.out.println("Le joueur1 a : " + nb_pions_j1);
		// System.out.println("Le joueur2 a : " + nb_pions_j2);
		
		if (nb_pions_j1 == nb_pions_j2) {
		    Msgbox.affMsg(f, "Match Null !!");
		    //System.out.println("Match nul !!!");
		} else {
		    if (nb_pions_j1 < nb_pions_j2) {
				Msgbox.affMsg(f, joueur2.getName() + " a gagn� !!");
				// System.out.println("Joueur2 a gagn� !!!");
		    } else {
				Msgbox.affMsg(f, joueur1.getName() + " a gagn� !!");
				// System.out.println("Joueur1 a gagn� !!!");
		    }
		}
		
		this.nouvellePartie();
		// System.exit(0);
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
     * @return <code>Joueur</code> joueur courant
     */
    public Joueur getJoueurCourant() {
		if (joueur1.getValue()==g.getTour()) {
			return joueur1;	
		} else {
			return joueur2;	
		}
    }
    
    /**
     * M�thode qui retourne le mode de jeu
     * @return <code>int</code> 1=mode 1 joueur, 2=mode 2 joueurs
     */
    public int getMode() {
    	return mode;	
    }
    
} // public class Jeu implements Constantes
