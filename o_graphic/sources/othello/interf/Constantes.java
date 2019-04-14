package othello.interf;

/**
 * Interface Constantes
 * permet de définir toutes les constantes nécessaires pour le jeu.
 * 
 * @author Mathieu HETRU & Frédéric MAQUET
 * @version 1.0,21 Mars 2003
 */
public interface Constantes {

	/** hauteur de la case */    
    final static int HC = 40;
    
    /** largeur de la case */
    final static int LC = 40;
	
	/** hauteur du plateau */
	final static int HP = 320;
	
	/** largeur du plateau */
	final static int LP = 320;
    
    /** constante pour symboliser le joueur1 */
    final static int JOUEUR1 = 1;
    
    /** constante pour symboliser le joueur2 */
    final static int JOUEUR2 = -1;
	
	/** constante pour symboliser le joueur humain */
	final static int HUMAN = 2;
	
	/** constante pour symboliser le joueur ordinateur */
	final static int COMPUTER = 3;
	
    /**  pour symboliser le joueur1 à l'écran */
    final static char P1='X';

    /** constante pour symboliser le joueur2 à l'écran */
    final static char P2='O';

    /** constante pour symboliser une case sans pions */
    final static String CASE_INACTIVE = ".";
    
    /** constante qui va définir la taille de la grille */
    final static int SIZE = 8;
      
    /** Tableau qui contient toutes les directions pour la recherche des coups possibles */
    final static String[] TAB_DIR = { "Nord", "NordEst", "Est", "SudEst", "Sud", "SudOuest", "Ouest", "NordOuest" }; // tab_cell de directions (8 directions)
    
    /** tableau qui contient les valeurs de toutes les cases pour l'évaluation de la grille*/
    final static int[][] EVAL = { { 100, -10, 11, 6, 6, 11, -10, 100},
    							  { -10, -20,  1, 2, 2, 1, -20, -10},
    							  {  10,   1,  5, 4, 4, 5,   1,  10},
   				 				  {   6,   2,  4, 2, 2, 4,   2,   6},
    							  {   6,   2,  4, 2, 2, 4,   2,   6},
    							  {  10,   1,  5, 4, 4, 5,   1,  10},
    							  { -10, -20,  1, 2, 2, 1, -20, -10},
    				  			  { 100, -10, 11, 6, 6, 11,-10, 100} };
} // public interface Constantes
