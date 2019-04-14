package othello.objet;

import java.util.*;
import othello.interf.*;
import othello.objet.*;

/**
 * Classe Grille
 * permet de d�finir une Grille
 *
 * @author Mathieu HETRU & Fr�d�ric MAQUET
 * @version 1.0,21 Mars 2003
 */
public class Grille implements Grid, Constantes {
    // attributs
    /** tableau de Cell */
    private Cell[][] tab_cell;
    /** liste cha�n�es de positions possibles */
    private LinkedList liste_pp; // liste chain�e pour m�moriser les positions possibles du joueur
    /** tour de d�part repr�sentant la valeur du joueur : au d�but, en dur c'est le joueur1 qui commence = humain */
    private int tour = JOUEUR1;
    /** variable compteur : incr�ment�e � chaque fois qu'un joueur joue */
    private int compteur = 0;

    /**
     * Constructeur de la classe Grille
     * @param <code>int</code> nombre de ligne de la grille � cr�er
     * @param <code>int</code> nombre de colonnes de la grille � cr�er
     */
    public Grille(int ligne, int colonne) {
	tab_cell = new Cell[ligne][colonne];
	liste_pp = new LinkedList();
    }
    
    /**
     * Constructeur d'une grille dupliqu�e
     * � partir d'une grille donn�e
     * @param <code>Grille</code> Grille source ... grille � copier
     */
    public Grille(Grille gr) {
	compteur = new Integer(gr.getCompteur()).intValue();
	tab_cell = new Cell[gr.getHeight()][gr.getWidth()];
	liste_pp = new LinkedList();
	// on parcourt la grille
	for(int y=0; y<gr.getHeight(); y++) {
	    for(int x=0; x<gr.getWidth(); x++) {
		tab_cell[y][x] = new Cell(gr.getCellAt(new Position(y,x,EVAL[y][x]))); // on cr�e une cell � partir d'une autre
	    }
	}
	tour=gr.getTour();
	this.initVoisins(); // on cr�e les voisins en mm temps
    }
    
    /**
     * M�thode qui renvoit la valeur du compteur
     * @return <code>int</code> valeur du compteur
     */
    public int getCompteur() {
    	return compteur;
    }
	
    /**
     * M�thode qui initialise les objets "Cells" dans la grille
     */
    public void initialiser() {
    	
	for(int y=0; y<NBL; y++) {
	    
	    for(int x=0; x<NBC; x++) {
		
		tab_cell[y][x]=new Cell(new Position(y,x,EVAL[y][x]));
		
	    } // for(int x=0; x<NBC; x++)
	    
	} // for(int y=0; y<NBL; y++)
	
    } // public void initialiser()
	
    /**
     * M�thode qui initialise les 4 pions de d�parts
     */
    public void initPions() {
	tab_cell[3][3].reverseChar(JOUEUR1);
	tab_cell[3][4].reverseChar(JOUEUR2);
	tab_cell[4][3].reverseChar(JOUEUR2);
	tab_cell[4][4].reverseChar(JOUEUR1);
    } // public void initPions()
    
    /**
     * M�thode qui initialise les 8 voisins de chaque cellule (Nord-Ouest, Nord, Nord-Est, ...)
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
     * M�thode qui retourne la hauteur de la grille
     * @return <code>int</code> hauteur de la grille
     */
    public int getHeight() {
    	return NBL;
    }
   	
    /**
     * M�thode qui retourne la largeur de la grille
     * @return <code>int</code> largeur de la grille
     */
    public int getWidth() {
    	return NBC;
    }
    
    /**
     * M�thode qui renvoit une cellule � une position donn�e
     * @param <code>Position</code> position donn�e (compos�e d'une ordonn�e y et d'une abscisse x)
     * @return <code>Cell</code> celulle renvoy�e
     */
    public Cell getCellAt(Position p) {
    	return tab_cell[p.getY()][p.getX()];
    }
    
    /**
     * M�thode qui affiche la grille � l'�cran
     */
    public void affiche() {
    	
	// AFFICHAGE DES LIGNES
	System.out.println();// on passe � la ligne
	// on affiche la grille proprement dite MAIS A L'ENVERS !
	for(int y=(NBL-1); y>(-1); y--) {
	    System.out.print(" " + (y+1));// on affiche le num�ro de la ligne
	    for(int x=0; x<NBC; x++) { // pour chaque colonne
		System.out.print(" " + tab_cell[y][x]);// on affiche la colonne
	    }
	    System.out.println();// on passe � la ligne
	}
	
	
	// AFFICHAGE DES COLONNES
	//System.out.println(); // passage � la ligne
	System.out.print("  "); // j'affiche un espace de 3
	
	char col_car='a';
	int compteur=1;
	
	do {
	    System.out.print(" " + col_car);// de colonne en colonne on affiche le nom de la colonne
	    compteur++;
	    col_car++;
	} while(compteur != (NBC+1)); // afficher le caract�re de la colonne tant que la colonne n'est pas �gale au nb de colonnes totales
	
	System.out.println();// passage � la ligne
    } // public void affiche

    /**
     * M�thode qui met � jour la liste cha�n�e des positions possibles d'un joueur donn�e
     * @param <code>int</code> valeur du joueur
     */
    public void miseAjourPositionsPossibles(int joueur_maj) {
	liste_pp.clear(); // � chaque tour on vide la liste
	
	// intialisation des variables de la fonction
	String direction_courante = "";
	Position position_possible = null;
	
	for(int y=0;y<NBL;y++) { // on scanne toutes les lignes
	    
	    for(int x=0; x<NBC; x++) { // on scanne toutes les colonnes
		
		if (joueur_maj == tab_cell[y][x].quelJoueur()) { // si on tombe sur un pion du joueur courant
		    
		    // pour chaque direction donn�e on cherche les positions possibles
		    for(int compteur=0; compteur<TAB_DIR.length; compteur++) {
			
			direction_courante = TAB_DIR[compteur]; // on r�cup�re la direction courante
			position_possible = null;
			position_possible = this.parcoursGrille(tab_cell[y][x].getPosition(), direction_courante, joueur_maj); // on recherche 
			
			// si c'est une position possible
			if (position_possible != null) {
			    // si la position trouv�e ne fait pas partie des positions possible 
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
     * M�thode PRIVATE qui permet de parcourir la grille vers une direction donn�e :
     * tant que le pion est celui du joueur adverse on parcourt ...
     * situation positive : on s'arrete sur un pion � nous (donc toute la rang�e de pions adverses est � nous)
     * situation n�gative : on s'arrete sur le bord de la grille
     * @param <code>Position</code> position de d�part
     * @param <code>String</code> direction � parcourir
     * @param <code>int</code> joueur concern�
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
		     * -soit on est sur un pion � nous :-)
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
	    // si on est arriv� ici c'est qu'on est sorti du tableau ou qu'il n'y a pas de voisin "dir"
	    pos_trouve = null; // donc c'est pas ok
	} // catch(Exception e)
	
	return pos_trouve;
    } // private boolean parcoursGrille(Position p, String dir)
    
    
    /**
     * M�thode qui retourne les pions sur la grille � partir d'un position de d�part
     * @param <code>Position</code> position de d�part
     * @param <code>int</code> joueur concern�
     */
    public void retournePions(Position p_depart, int joueur_concerne) {
    	// initialisation des variables
    	String direction_courante = "";
    	LinkedList positions_trouves = new LinkedList();
    	Iterator it = null;
    	Object o = null;
    	Position p = null;

    	tab_cell[p_depart.getY()][p_depart.getX()].reverseChar(joueur_concerne);

    	// pour chaque direction donn�e on cherche les positions possibles
	for(int compteur=0; compteur<TAB_DIR.length; compteur++) {
	    
	    direction_courante = TAB_DIR[compteur]; // on r�cup�re la direction courante
	    positions_trouves = null;
	    positions_trouves = this.parcoursRetourne(p_depart, direction_courante, joueur_concerne); // on recherche 
	    
	    // si la liste des positions trouves (positions � retourner) existe .. alors on retourne les pions ;-)
	    if (positions_trouves != null) {
		
		it = positions_trouves.iterator();
		
		while(it.hasNext()) { // on parcoure la liste
		    
		    o = it.next(); // on r�cup�re l'objet point�
		    p = (Position) o; // on le "caste" en position
		    tab_cell[p.getY()][p.getX()].reverseChar(joueur_concerne); // on retourne le pion ;-)
		    
		}// while(it.hasNext())
		
	    }// if (positions_trouves != null)
	    
	} // for(int compteur=0; compteur<directions.length; compteur++) { // directions

    } // private void retournePions(Position p_depart)
    
    /**
     * Retourne la liste des pions � retourner
     * @param <code>Position</code> position de d�part
     * @param <code>String</code> direction
     * @param <code>int</code> joueur concern�
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
		     * -soit une case � nous
		     */
		    if (c_tmp.quelJoueur() == joueur_retourne) {//si le pion est � nous : ok on sort et on garde la liste des positions
			parcourt=false;
		    } else {// sinon on met la liste � null et on sort
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
	    // si on est arriv� ici c'est qu'on est sorti du tableau ou qu'il n'y a pas de voisin "dir"
	    liste_positions_trouves = null; // donc c'est pas ok
	} // catch(Exception e)
	
	return liste_positions_trouves;
    } // private LinkedList parcoursRetourne(Position p_depart, String direction)
    
	/**
	 * M�thode qui renvoit la liste des positions possibles
	 * @return <code>Linkedlist</code> liste des positions possibles
	 */
    public LinkedList getListePositionsPossibles() {
    	return liste_pp;
    }
    
    /**
     * M�thode qui alterne le joueur
     */
    public void alterneJoueur() {
    	tour = (-tour);
    }
    
    /**
     * M�thode qui retourne la valeur du joueur courant
     * @param <code>int</code> valeur du joueur courant
     */
    public int getTour() {
	return tour;
    }
    
    /**
     * M�thode qui renvoit le nb de pion d'un joueur donn� en parametre
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
     * m�thode qui renvoit le num�ro du joueur
     * @param <code>int</code> valeur du joueur
     * @return <code>int</code> num�ro du joueur
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
     * M�thode qui renvoit le caract�re du pion d'un joueur donn�
     * @param <code>int</code> valeur du joueur
     * @return <code>String</code> chaine de caract�re repr�sentant le pion du joueur
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
     * M�thode qui r�cupere la valeur d'une evaluation du tableau EVAL � partir d'une position donn�e
     * @param <code>Position</code> position donn�e
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
     * Fonction d'�valuation qui permet d'�valuer une grille
     * @param <code>int</code> valeur du joueur
     * @return <code>int</code> valeur de la grille
     */
    public int evaluationGrille(int joueur_courant) {
	int notrePosition = 0;
	int notreMobilite = 0;
	int saPosition = 0;
	int saMobilite = 0;
	int mobiliteTableau = 0;
	// � chaque fois qu'un joueur retourne les pions, le compteur est incr�ent�
	int recent = 12;  // au bout de 12 tours ou plus, le jeu est de type "r�cent"
	int tard = 55; // au bout de 55 tours, le jeu de type "tard"
	
	
	// si le jeu est de type tard
	if (compteur > tard) {
	    mobiliteTableau = -15; // l'indice t�moin de mobilit� est valu� � -15
	} else if (compteur < recent) {  
	    mobiliteTableau = -2; // jeu de type r�cent, indice t�moin valu� � -2
	} else {
	    mobiliteTableau = 3; // si le jeu est entre "recent" et "tard"
	}
	
	// on parcourt s�quentiellement la grille
	for (int y=0; y<NBL; y++){ // pour chaque ligne
	    for (int x=0; x<NBC; x++) { // pour chaque colonne
		if (this.getCellAt(new Position(y,x)).quelJoueur() == joueur_courant) { // le pion est un pion du "joueur_courant"
		    notrePosition += EVAL[y][x]; // on ajoute � sa position, la valeur de son pion
		    saMobilite += mobiliteTableau; // on ajout � sa mobilit�, la valeur de sa moibilit� du t�moin de mobilit�
		} else if (this.getCellAt(new Position(y,x)).quelJoueur() == -joueur_courant) {// pareil pour le joueur adverse
		    saPosition += EVAL[y][x];
		    notreMobilite += mobiliteTableau;
		}
	    }
	}
	
	// on effectue la diff�rence entre (la somme des valeurs des pions du "joueur_courant" avec la somme des mobilit�s possibles) et du joueur adverse
	int total = (notrePosition + notreMobilite) - (saPosition + saMobilite);
	return total;
    }
    
}










