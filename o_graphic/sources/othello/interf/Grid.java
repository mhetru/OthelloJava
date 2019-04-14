package othello.interf;

import java.util.*;
import othello.objet.*;

/**
 * Interface Grid
 * permet de d�finir une Grille de jeu Othello
 *
 * @author Mathieu HETRU & Fr�d�ric MAQUET
 * @version 1.0,21 Mars 2003
 */
public interface Grid {
	
	/** de r�cup�rer la valeur du compteur d'alternance */
    public int getCompteur();
    
    /** permet d'initialiser une grille */
    public void initialiser();
    
    /** permet d'initialiser les pions sur la grille */
    public void initPions();
    
    /** permet d'initialiser tous les voisins de chaque pions sur la grille */
    public void initVoisins();
    
    /** permet de r�cup�rer la hauteur d'une grille */
    public int getHeight();

    /** permet de r�cup�rer la largeur d'une grille */
    public int getWidth();
    
    /** permet de r�cup�rer une celulle d'un grille � la position p */
    public Cell getCellAt(Position p);
    
    /** permet d'afficher la grille */
    public void affiche();
    
    /** permet de mettre � jour les positions possibles d'un joueur donn� */
    public void miseAjourPositionsPossibles(int joueur_maj);
    
    
    // private Position parcoursGrille(Position p, String dir, int joueur_concerne);
    
    /** permet de retourner un pion � partir du joueur concern� */
    public void retournePions(Position p_depart, int joueur_concerne);
    
    // private LinkedList parcoursRetourne(Position p_dep, String dir, int joueur_retourne);
    
    /** permet de r�cup�rer les positions possibles */
    public LinkedList getListePositionsPossibles();

	/** permet d'alterner le joueur */
    public void alterneJoueur();
    
    /** permet de r�cup�rer le joueur courant */
    public int getTour();

	/** permet de r�cup�rer le nb de pions d'un joueur pass� en param�tre */
    public int getNbPions(int i);

	/** permet de r�cup�rer un num�ro de joueur en fonction de sa valeur */
    public int getNbJoueur(int n);

    /** permet de r�cup�rer le caract�re du pion du joueur pass� en param�tre */
    public String getPion(int n);

    /** permet de r�cup�rer la valeur d'une cell � une position donn�e */
    public int getVal(Position p);

	/** permet de r�cup�rer l'�valuation d'une grille */
    public int evaluationGrille(int joueur_courant);

} // public interface Grid
