package othello.interf;

import java.awt.*;

/**
 * Interface Constantes
 * permet de d�finir toutes les constantes n�cessaires pour le jeu.
 * 
 * @author Mathieu HETRU & Fr�d�ric MAQUET
 * @version 1.0,21 Mars 2003
 */
public interface Constantes {
	
    /** constante pour symboliser le joueur1 */
    final static int JOUEUR1 = 1;
    
    /** constante pour symboliser le joueur2 */
    final static int JOUEUR2 = -1;

    /**  pour symboliser le joueur1 � l'�cran */
    final static char P1='X';

    /** constante pour symboliser le joueur2 � l'�cran */
    final static char P2='O';

    /** constante pour symboliser une case sans pions */
    final static String CASE_INACTIVE = ".";
    
    /** constante pour symboliser la couleur noire */
    final static Color WHITE = Color.white;
    
    /** constante pour symboliser la couleur blanche */
    final static Color BLACK = Color.black;
    
    /** constante qui va d�finir le nombre de colonnes de la grille */
    final static int NBC = 8;
    
    /** constante qui va d�finir le nombre de lignes de la grille */
    final static int NBL = 8;
    
    /** constante qui permet de d�finir la profondeur pour l'IA */
    final static int PROFONDEUR = 4;
    
    /** Tableau qui contient toutes les directions pour la recherche des coups possibles */
    final static String[] TAB_DIR = { "Nord", "NordEst", "Est", "SudEst", "Sud", "SudOuest", "Ouest", "NordOuest" }; // tab_cell de directions (8 directions)
    
    /** tableau qui contient les valeurs de toutes les cases pour l'�valuation de la grille*/
    final static int[][] EVAL = { { 100, -10, 11, 6, 6, 11, -10, 100},
    							  { -10, -20,  1, 2, 2, 1, -20, -10},
    							  {  10,   1,  5, 4, 4, 5,   1,  10},
   				 				  {   6,   2,  4, 2, 2, 4,   2,   6},
    							  {   6,   2,  4, 2, 2, 4,   2,   6},
    							  {  10,   1,  5, 4, 4, 5,   1,  10},
    							  { -10, -20,  1, 2, 2, 1, -20, -10},
    				  			  { 100, -10, 11, 6, 6, 11,-10, 100} };
} // public interface Constantes
