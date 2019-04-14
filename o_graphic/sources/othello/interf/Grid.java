package othello.interf;

import java.util.*;
import othello.objet.*;

/**
 * Interface Grid
 * permet de définir une Grille de jeu Othello
 *
 * @author Mathieu HETRU & Frédéric MAQUET
 * @version 1.0,21 Mars 2003
 */
public interface Grid {
	
	/** de récupérer la valeur du compteur d'alternance */
    public int getCompteur();
    
    /** permet d'initialiser une grille */
    public void initialiser();
    
    /** permet d'initialiser les pions sur la grille */
    public void initPions();
    
    /** permet d'initialiser tous les voisins de chaque pions sur la grille */
    public void initVoisins();
    
    /** permet de récupérer la hauteur d'une grille */
    public int getHeight();

    /** permet de récupérer la largeur d'une grille */
    public int getWidth();
    
    /** permet de récupérer une celulle d'un grille à la position p */
    public Cell getCellAt(Position p);
    
    /** permet d'afficher la grille */
    public void affiche();
    
    /** permet de mettre à jour les positions possibles d'un joueur donné */
    public void miseAjourPositionsPossibles(int joueur_maj);
    
    
    // private Position parcoursGrille(Position p, String dir, int joueur_concerne);
    
    /** permet de retourner un pion à partir du joueur concerné */
    public void retournePions(Position p_depart, int joueur_concerne);
    
    // private LinkedList parcoursRetourne(Position p_dep, String dir, int joueur_retourne);
    
    /** permet de récupérer les positions possibles */
    public LinkedList getListePositionsPossibles();

	/** permet d'alterner le joueur */
    public void alterneJoueur();
    
    /** permet de récupérer le joueur courant */
    public int getTour();

	/** permet de récupérer le nb de pions d'un joueur passé en paramètre */
    public int getNbPions(int i);

	/** permet de récupérer un numéro de joueur en fonction de sa valeur */
    public int getNbJoueur(int n);

    /** permet de récupérer le caractère du pion du joueur passé en paramètre */
    public String getPion(int n);

    /** permet de récupérer la valeur d'une cell à une position donnée */
    public int getVal(Position p);

	/** permet de récupérer l'évaluation d'une grille */
    public int evaluationGrille(int joueur_courant);

} // public interface Grid
