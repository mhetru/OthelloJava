package othello.objet;

import java.util.*;
import othello.interf.*;
import othello.objet.*;

/**
 * Classe Grille
 * permet de définir une Grille
 *
 * @author Mathieu HETRU & Frédéric MAQUET
 * @version 1.0,21 Mars 2003
 */
public class Grille implements Grid, Constantes {
    // attributs
    /** tableau de Cell */
    private Cell[][] tab_cell;
    /** liste chaînées de positions possibles */
    private LinkedList liste_pp; // liste chainée pour mémoriser les positions possibles du joueur
    /** tour de départ représentant la valeur du joueur : au début, en dur c'est le joueur1 qui commence = humain */
    private int tour = JOUEUR1;
    /** variable compteur : incrémentée à chaque fois qu'un joueur joue */
    private int compteur = 0;

    /**
     * Constructeur de la classe Grille
     * @param <code>int</code> nombre de ligne de la grille à créer
     * @param <code>int</code> nombre de colonnes de la grille à créer
     */
    public Grille(int ligne, int colonne) {
	tab_cell = new Cell[ligne][colonne];
	liste_pp = new LinkedList();
    }
    
    /**
     * Constructeur d'une grille dupliquée
     * à partir d'une grille donnée
     * @param <code>Grille</code> Grille source ... grille à copier
     */
    public Grille(Grille gr) {
	compteur = new Integer(gr.getCompteur()).intValue();
	tab_cell = new Cell[gr.getHeight()][gr.getWidth()];
	liste_pp = new LinkedList();
	// on parcourt la grille
	for(int y=0; y<gr.getHeight(); y++) {
	    for(int x=0; x<gr.getWidth(); x++) {
		tab_cell[y][x] = new Cell(gr.getCellAt(new Position(y,x,EVAL[y][x]))); // on crée une cell à partir d'une autre
	    }
	}
	tour=gr.getTour();
	this.initVoisins(); // on crée les voisins en mm temps
    }
    
    /**
     * Méthode qui renvoit la valeur du compteur
     * @return <code>int</code> valeur du compteur
     */
    public int getCompteur() {
    	return compteur;
    }
	
    /**
     * Méthode qui initialise les objets "Cells" dans la grille
     */
    public void initialiser() {
    	
	for(int y=0; y<NBL; y++) {
	    
	    for(int x=0; x<NBC; x++) {
		
		tab_cell[y][x]=new Cell(new Position(y,x,EVAL[y][x]));
		
	    } // for(int x=0; x<NBC; x++)
	    
	} // for(int y=0; y<NBL; y++)
	
    } // public void initialiser()
	
    /**
     * Méthode qui initialise les 4 pions de départs
     */
    public void initPions() {
	tab_cell[3][3].reverseChar(JOUEUR1);
	tab_cell[3][4].reverseChar(JOUEUR2);
	tab_cell[4][3].reverseChar(JOUEUR2);
	tab_cell[4][4].reverseChar(JOUEUR1);
    } // public void initPions()
    
    /**
     * Méthode qui initialise les 8 voisins de chaque cellule (Nord-Ouest, Nord, Nord-Est, ...)
     */
    public void initVoisins() {
	for(int y=0; y<NBL; y++) {
	    for(int x=0; x<NBC; x++) {
		if (y > 0) {
		    tab_cell[y][x].add("Sud", tab_cell[y-1][x]);
		}
		if (y < (NBL-1)) {
		    tab_cell[y][x].add("Nord", tab_cell[y+1][x]);
		}
		if (x > 0) {
		    tab_cell[y][x].add("Ouest", tab_cell[y][x-1]);
		}
		if (x < (NBC-1)) {
		    tab_cell[y][x].add("Est", tab_cell[y][x+1]);
		}
		if ((y > 0) && (x > 0)) {
		    tab_cell[y][x].add("SudOuest", tab_cell[y-1][x-1]);
		}
		if ((y > 0) && (x <(NBC-1))) {
		    tab_cell[y][x].add("SudEst", tab_cell[y-1][x+1]);
		}
		if ((y < (NBL-1)) && (x > 0)) {
		    tab_cell[y][x].add("NordOuest", tab_cell[y+1][x-1]);
		}
		if ((y < (NBL-1)) && (x < (NBC-1))) {
		    tab_cell[y][x].add("NordEst", tab_cell[y+1][x+1]);
		}
	    } // for(int x=0; x<NBC; x++)
	} // for(int y=0; y<NBL; y++)
    } // public void initVoisins
    
    /**
     * Méthode qui retourne la hauteur de la grille
     * @return <code>int</code> hauteur de la grille
     */
    public int getHeight() {
    	return NBL;
    }
   	
    /**
     * Méthode qui retourne la largeur de la grille
     * @return <code>int</code> largeur de la grille
     */
    public int getWidth() {
    	return NBC;
    }
    
    /**
     * Méthode qui renvoit une cellule à une position donnée
     * @param <code>Position</code> position donnée (composée d'une ordonnée y et d'une abscisse x)
     * @return <code>Cell</code> celulle renvoyée
     */
    public Cell getCellAt(Position p) {
    	return tab_cell[p.getY()][p.getX()];
    }
    
    /**
     * Méthode qui affiche la grille à l'écran
     */
    public void affiche() {
    	
	// AFFICHAGE DES LIGNES
	System.out.println();// on passe à la ligne
	// on affiche la grille proprement dite MAIS A L'ENVERS !
	for(int y=(NBL-1); y>(-1); y--) {
	    System.out.print(" " + (y+1));// on affiche le numéro de la ligne
	    for(int x=0; x<NBC; x++) { // pour chaque colonne
		System.out.print(" " + tab_cell[y][x]);// on affiche la colonne
	    }
	    System.out.println();// on passe à la ligne
	}
	
	
	// AFFICHAGE DES COLONNES
	//System.out.println(); // passage à la ligne
	System.out.print("  "); // j'affiche un espace de 3
	
	char col_car='a';
	int compteur=1;
	
	do {
	    System.out.print(" " + col_car);// de colonne en colonne on affiche le nom de la colonne
	    compteur++;
	    col_car++;
	} while(compteur != (NBC+1)); // afficher le caractère de la colonne tant que la colonne n'est pas égale au nb de colonnes totales
	
	System.out.println();// passage à la ligne
    } // public void affiche

    /**
     * Méthode qui met à jour la liste chaînée des positions possibles d'un joueur donnée
     * @param <code>int</code> valeur du joueur
     */
    public void miseAjourPositionsPossibles(int joueur_maj) {
	liste_pp.clear(); // à chaque tour on vide la liste
	
	// intialisation des variables de la fonction
	String direction_courante = "";
	Position position_possible = null;
	
	for(int y=0;y<NBL;y++) { // on scanne toutes les lignes
	    
	    for(int x=0; x<NBC; x++) { // on scanne toutes les colonnes
		
		if (joueur_maj == tab_cell[y][x].quelJoueur()) { // si on tombe sur un pion du joueur courant
		    
		    // pour chaque direction donnée on cherche les positions possibles
		    for(int compteur=0; compteur<TAB_DIR.length; compteur++) {
			
			direction_courante = TAB_DIR[compteur]; // on récupère la direction courante
			position_possible = null;
			position_possible = this.parcoursGrille(tab_cell[y][x].getPosition(), direction_courante, joueur_maj); // on recherche 
			
			// si c'est une position possible
			if (position_possible != null) {
			    // si la position trouvée ne fait pas partie des positions possible 
			    if (liste_pp.contains(position_possible) == false) {
				liste_pp.add(position_possible); // alors on l'ajoute
			    }
			}
			
		    } // for(int compteur=0; compteur<directions.length; compteur++) { // directions
		    
		} // if (tour == tab_cell[y][x].quelJoueur()) {//si on tombe sur un pion du joueur courant
		
	    } // for(int x=0; x<NBC; x++) {//on scanne toutes les colonnes
	    
	    
	} //for(int y=0;y<NBL;y++) {//on scanne toutes les lignes
	
	
    }
    
    /**
     * Méthode PRIVATE qui permet de parcourir la grille vers une direction donnée :
     * tant que le pion est celui du joueur adverse on parcourt ...
     * situation positive : on s'arrete sur un pion à nous (donc toute la rangée de pions adverses est à nous)
     * situation négative : on s'arrete sur le bord de la grille
     * @param <code>Position</code> position de départ
     * @param <code>String</code> direction à parcourir
     * @param <code>int</code> joueur concerné
     */
    private Position parcoursGrille(Position p, String dir, int joueur_concerne) {
	// Initialisation des variables
	Position pos_trouve = null;
	boolean parcourt = true;
	int x = p.getX();
	int y = p.getY();
	Cell c_tmp = tab_cell[y][x];
	Object ob = null;
	int nb_voisins_trouves = 0;
	
	// ici on fait un try .. catch car on peut sortir du tableau !!!
	try {
	    while(parcourt == true) {
		ob = c_tmp.getVoisins().get(dir);
		
		if (ob == null) {// si l'objet est "null" c'est qu'il n'existe pas
		    throw new Exception("il n'y a pas de voisin du " + dir);
		}// if (ob == null)
		
		c_tmp = (Cell) ob;
		
		if (c_tmp.quelJoueur() == (-joueur_concerne)) { // si le pion est un pion adverse
		    nb_voisins_trouves++;
		}// if (c_tmp.quelJoueur() == (-tour))
		
		if (c_tmp.quelJoueur() != (-joueur_concerne)) { // si le pion n'est pas un pion adverse
		    
		    /**
		     * alors
		     * -soit on est sur un pion à nous :-)
		     * -soit on est sur une case vide
		     */
		    if ((c_tmp.quelJoueur() == 0) && (nb_voisins_trouves > 0)) {
			pos_trouve = c_tmp.getPosition();
		    } else {
			pos_trouve = null;
		    }
		    parcourt = false;
		    
		} else {// sinon si le pion du voisin est un pion adverse
		    
		    // on recupere son voisin
		    ob = c_tmp.getVoisins().get(dir);
		    
		}// if (c_tmp.quelJoueur() == -(tour))
		
	    }// while(parcourt == true)
	    
	} catch(Exception e) {
	    //System.out.println("Erreur : " + e.getMessage());
	    // si on est arrivé ici c'est qu'on est sorti du tableau ou qu'il n'y a pas de voisin "dir"
	    pos_trouve = null; // donc c'est pas ok
	} // catch(Exception e)
	
	return pos_trouve;
    } // private boolean parcoursGrille(Position p, String dir)
    
    
    /**
     * Méthode qui retourne les pions sur la grille à partir d'un position de départ
     * @param <code>Position</code> position de départ
     * @param <code>int</code> joueur concerné
     */
    public void retournePions(Position p_depart, int joueur_concerne) {
    	// initialisation des variables
    	String direction_courante = "";
    	LinkedList positions_trouves = new LinkedList();
    	Iterator it = null;
    	Object o = null;
    	Position p = null;

    	tab_cell[p_depart.getY()][p_depart.getX()].reverseChar(joueur_concerne);

    	// pour chaque direction donnée on cherche les positions possibles
	for(int compteur=0; compteur<TAB_DIR.length; compteur++) {
	    
	    direction_courante = TAB_DIR[compteur]; // on récupère la direction courante
	    positions_trouves = null;
	    positions_trouves = this.parcoursRetourne(p_depart, direction_courante, joueur_concerne); // on recherche 
	    
	    // si la liste des positions trouves (positions à retourner) existe .. alors on retourne les pions ;-)
	    if (positions_trouves != null) {
		
		it = positions_trouves.iterator();
		
		while(it.hasNext()) { // on parcoure la liste
		    
		    o = it.next(); // on récupère l'objet pointé
		    p = (Position) o; // on le "caste" en position
		    tab_cell[p.getY()][p.getX()].reverseChar(joueur_concerne); // on retourne le pion ;-)
		    
		}// while(it.hasNext())
		
	    }// if (positions_trouves != null)
	    
	} // for(int compteur=0; compteur<directions.length; compteur++) { // directions

    } // private void retournePions(Position p_depart)
    
    /**
     * Retourne la liste des pions à retourner
     * @param <code>Position</code> position de départ
     * @param <code>String</code> direction
     * @param <code>int</code> joueur concerné
     */
    private LinkedList parcoursRetourne(Position p_dep, String dir, int joueur_retourne) {
    	
    	// Initialisation des variables
	boolean parcourt = true;
	int x = p_dep.getX();
	int y = p_dep.getY();
	Cell c_tmp = tab_cell[y][x];
	Object ob = null;
	LinkedList liste_positions_trouves = new LinkedList();
	
	// ici on fait un try .. catch car on peut sortir du tableau !!!
	try {
	    while(parcourt == true) {// tant qu'on peut parcourir dans la direction "dir"
		ob = c_tmp.getVoisins().get(dir);
		
		if (ob == null) {// si l'objet est "null" c'est qu'il n'existe pas
		    throw new Exception("il n'y a pas de voisin du " + dir);
		}
		
		c_tmp = (Cell) ob;// on "caste" l'objet en "Cell"
		
		if (c_tmp.quelJoueur() == (-joueur_retourne)) { // si le pion est un pion adverse
		    liste_positions_trouves.add(c_tmp.getPosition()); // on ajoute sa position
		}
		
		if (c_tmp.quelJoueur() != (-joueur_retourne)) {// si le pion n'est pas un pion adverse
		    /**
		     * c'est que :
		     * -soit c'est une case vide
		     * -soit une case à nous
		     */
		    if (c_tmp.quelJoueur() == joueur_retourne) {//si le pion est à nous : ok on sort et on garde la liste des positions
			parcourt=false;
		    } else {// sinon on met la liste à null et on sort
			liste_positions_trouves = null;
			parcourt=false;
		    }
		} else {// sinon si le pion du voisin est un pion adverse
		    
		    // on recupere son voisin
		    ob = c_tmp.getVoisins().get(dir);
		    
		}// if (c_tmp.quelJoueur() == -(tour))
		
	    }// while(parcourt == true)
	    
	} catch(Exception e) {
	    //System.out.println("Erreur : " + e.getMessage());
	    // si on est arrivé ici c'est qu'on est sorti du tableau ou qu'il n'y a pas de voisin "dir"
	    liste_positions_trouves = null; // donc c'est pas ok
	} // catch(Exception e)
	
	return liste_positions_trouves;
    } // private LinkedList parcoursRetourne(Position p_depart, String direction)
    
	/**
	 * Méthode qui renvoit la liste des positions possibles
	 * @return <code>Linkedlist</code> liste des positions possibles
	 */
    public LinkedList getListePositionsPossibles() {
    	return liste_pp;
    }
    
    /**
     * Méthode qui alterne le joueur
     */
    public void alterneJoueur() {
    	tour = (-tour);
    }
    
    /**
     * Méthode qui retourne la valeur du joueur courant
     * @param <code>int</code> valeur du joueur courant
     */
    public int getTour() {
	return tour;
    }
    
    /**
     * Méthode qui renvoit le nb de pion d'un joueur donné en parametre
     * @param <code>int</code> valeur du joueur
	 * @return <code>int</code> nb de pions
	 */
    public int getNbPions(int i) {
	int tmp = 0;
	
	for(int y=0; y<NBL; y++) {
	    
	    for(int x=0; x<NBC; x++) {
		
		if (tab_cell[y][x].quelJoueur() == i) {
		    tmp++;
		}
		
	    } // for(int x=0; x<NBC; x++)
	    
	} // for(int y=0; y<NBL; y++)
	
	return tmp;
    }
    
    /**
     * méthode qui renvoit le numéro du joueur
     * @param <code>int</code> valeur du joueur
     * @return <code>int</code> numéro du joueur
     */
    public int getNbJoueur(int n) {
	int tmp = 0;
	if (n == 1) {
	    tmp = 1;
	} else {
	    tmp = 2;
	}
	return tmp;
    }
    
    /**
     * Méthode qui renvoit le caractère du pion d'un joueur donné
     * @param <code>int</code> valeur du joueur
     * @return <code>String</code> chaine de caractère représentant le pion du joueur
     */
    public String getPion(int n) {
	String retour = " ";
	char c = 'a';
	if (n == 1) { // joueur1
	    c = P1;
	} else {
	    c = P2; // joueur2
	}
	char[] tmp = { c };
	retour = new String(tmp); // alors on retourne son caractere
		return retour;
    }
    
    /**
     * Méthode qui récupere la valeur d'une evaluation du tableau EVAL à partir d'une position donnée
     * @param <code>Position</code> position donnée
     * @return <code>int</code> valeur de la position
     */
    public int getVal(Position p) {
	return EVAL[p.getY()][p.getX()];
    }
    
    // fonction d'evaluation
    // renvoit la valeur de la grille
    /*public int evaluationGrille(int joueur_courant) {
      //Cell tmp = null;
      int compteur = 0;
      // on scanne toute la grille
      for (int y=0;y<NBL;y++) {
      for(int x=0; x<NBC; x++) {
      // si la case courante appartient au joueur courant
      if (this.getCellAt(new Position(y,x)).quelJoueur() == joueur_courant) {
      compteur += this.getVal(new Position(y,x));
      }
      }
      }
      return compteur;
    }*/
    
    /**
     * Fonction d'évaluation qui permet d'évaluer une grille
     * @param <code>int</code> valeur du joueur
     * @return <code>int</code> valeur de la grille
     */
    public int evaluationGrille(int joueur_courant) {
	int notrePosition = 0;
	int notreMobilite = 0;
	int saPosition = 0;
	int saMobilite = 0;
	int mobiliteTableau = 0;
	// à chaque fois qu'un joueur retourne les pions, le compteur est incréenté
	int recent = 12;  // au bout de 12 tours ou plus, le jeu est de type "récent"
	int tard = 55; // au bout de 55 tours, le jeu de type "tard"
	
	
	// si le jeu est de type tard
	if (compteur > tard) {
	    mobiliteTableau = -15; // l'indice témoin de mobilité est valué à -15
	} else if (compteur < recent) {  
	    mobiliteTableau = -2; // jeu de type récent, indice témoin valué à -2
	} else {
	    mobiliteTableau = 3; // si le jeu est entre "recent" et "tard"
	}
	
	// on parcourt séquentiellement la grille
	for (int y=0; y<NBL; y++){ // pour chaque ligne
	    for (int x=0; x<NBC; x++) { // pour chaque colonne
		if (this.getCellAt(new Position(y,x)).quelJoueur() == joueur_courant) { // le pion est un pion du "joueur_courant"
		    notrePosition += EVAL[y][x]; // on ajoute à sa position, la valeur de son pion
		    saMobilite += mobiliteTableau; // on ajout à sa mobilité, la valeur de sa moibilité du témoin de mobilité
		} else if (this.getCellAt(new Position(y,x)).quelJoueur() == -joueur_courant) {// pareil pour le joueur adverse
		    saPosition += EVAL[y][x];
		    notreMobilite += mobiliteTableau;
		}
	    }
	}
	
	// on effectue la différence entre (la somme des valeurs des pions du "joueur_courant" avec la somme des mobilités possibles) et du joueur adverse
	int total = (notrePosition + notreMobilite) - (saPosition + saMobilite);
	return total;
    }
    
}










