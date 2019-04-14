package othello.interf;

import othello.objet.*;
/**
 *
 * Interface Game
 * contient les signatures des méthodes pour un jeu à un ou deux joueurs
 *
 * @author Mathieu HETRU & Frédéric MAQUET
 * @version 1.0,21 Mars 2003
 */
public interface Game {
	
	/** permet d'initialiser un jeu */
    public void init();

	/** permet d'afficher un jeu */
    public String afficheGrille();
    
   	/** permet d'afficher le nb de pions */
    public String afficheNbPions();
    
	/** permet de rechercher les positions possibles pour un joueur donné */    
    public void rechPositionsPossibles(int j);

	/** permet d'alterner les joueurs */
    public void alterneJoueur();

	/** permet de faire jouer l'ordinateur */
    public String faireJouerOrdi();
    
	/** permet de définir un alphaBeta */
    public Position alphaBeta(Grille gr, int joueur, int prof, int alpha, int beta, boolean isMax);

	/** permet de commander un jeu pour un joueur donné */
    public void commandes(String s, int j);

	/** permet de récupérer une grille */
    public Grille getGrille();

	/** permet de déclarer un gagnant */
    public void declarerGagnant();

	/** permet de récupérer le joueur courant */
    public int getJoueurCourant();
}
