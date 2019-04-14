package othello.objet;

import java.awt.*;
import java.util.*;
import othello.objet.*;
import othello.interf.*;

/**
 * Classe qui représente une cellule qui contient un pion.
 *
 * @author Mathieu HETRU & Frédéric MAQUET
 * @version 1.0,21 Mars 2003
 */
public class Cell implements Constantes {

    /** booléen qui permet de savoir si une cellule est active ou non */
    private boolean active = false; // cellule du pion (active = true, inactive = false) 
    
    /** objet position de la cellule */
    private Position pos;
    
    /** objet caractère qui afficher le conteneu de la cellule à l'écran */
    private char caractere;
    
    /** entier qui permet de distinguer le joueur 1 du joueur 2 */
    private int z=0; // entier qui permet de distinguer le joueur (1=JOUEUR1='x', -1=JOUEUR2='O')
    
    /** attribut couleur qui représente la couleur du pion */
    private Color couleur;
    
    /** Cette Hashtable contient tous les voisins d'une cellule donnée */
    private Hashtable voisins; // table de hachage pour stocker les 8 voisins de la Cell courante
    
    /**
     * Constructeur de la classe Cell, une cellule est composé d'une position et d'une liste 
     * de ses voisins
     */
    public Cell(Position p) {
		pos = p;
		this.voisins = new Hashtable();
    } // public Cell(Position p)
    
    
    /**
     * Ce constructeur permet de construire une autre cellule identique à celle passée en paramètre
     */
    public Cell(Cell c) {
		pos = new Position(c.getY(),c.getX(),new Integer(c.getPosition().getValue()).intValue());
		active = new Boolean(c.getEtat()).booleanValue();
		caractere = new Character(c.getChar()).charValue();
		z = new Integer(c.quelJoueur()).intValue();
		// couleur = new Color(c.getColor());
		voisins = new Hashtable();
    }
    
    /** 
     * Méthode qui retourne la colonne de la Cell
     * @return <code>int</code> entier représentant la colonne de la Cell
     */
    public int getX() {
	return pos.getX();
    } // public int getX()
    
    /** 
     * Méthode qui retourne la ligne de la Cell
     * @return <code>int</code> entier représentant la ligne de la Cell
     */
    public int getY() {
		return pos.getY();
    } // public int getY()
    
    /** 
     * Méthode qui retourne si la cellule est active
     * @return <code>boolean</code> booleén représentant l'état de la Cell
     */
    public boolean getEtat() {
		return this.active;
    } // public boolean getEtat()
    
    
    /**
     * Méthode qui rend active la cellule
     */
    private void rendreActive() {
		this.active = true;
    } // private void rendreActive()
    
    
    /** 
     * Méthode qui indique le caractère de la cellule
     * @return <code>char</code> Le caractère renvoyé sera X ou O ou .
     */
    public char getChar() {
	return this.caractere;
    } // public char getChar()
    
    /** 
     * Méthode qui renverse la caractère de la cellule en fonction du joueur passé en paramètre
     * @param <code> int </code> entier qui représente le joueur  
     */
    public void reverseChar(int z) {
	this.z=z;
	this.rendreActive();
	if (z == JOUEUR1) {
	    caractere = P1; // 'X' JOUEUR1
	} else {
	    caractere = P2; // 'O' JOUEUR2
	}
    } // public void reverseChar(int z)
    
    
    /**
     * Methode qui indique à quel joueur appartient la cellule
     * @return <code>int</code> cet entier indiquera à qui appartient la cellule
     */
    public int quelJoueur() {
	return this.z;
    } // public int QuelJoueur()

    
   
    
    
    /**
     * Méthode qui affiche à l'écran la cellule
     * @return <code>String</code> Le String renvoyé sera affiché à l'écran
     */
    public String toString() {
	String retour = CASE_INACTIVE;
	char[] tmp = { caractere }; // on récupère le caractère de la cellule
	if (active == true) { // si la case est active
	    retour = new String(tmp); // alors on retourne son caractere
	}
	return retour;
    } // public String toString()
    
    
    /**
     * Méthode qui renvoit la couleur du pion de la celulle courante
     * @return <code>Color</code> couleur du pion
     */
    public Color getColor() {
	return this.couleur;
    } // public Color getColor()
    
    
    /**
     * Méthode qui bascule la couleur du pion en couleur adverse
     * @param <code>int</code> valeur du joueur
     */
    public void reverseColor(int z) {
		this.z=z;
		// on choisit que 1 = JOUEUR1 = 'X' et -1 = JOUEUR2 = 'O'
		if (z == JOUEUR1) { // joueur1
		    couleur = WHITE;
		}  else { // joueur2
		    couleur = BLACK;
		}
    } // public void reverseColor(int z)
    
    /**
     * Methode qui renvoie la position de la cellule 
     * @return <code>Position</code> L'objet renvoyé sera la position de la cellule
     */
    public Position getPosition() {
		return pos;
    }
    
    /**
     * Methode qui renvoie la liste des voisins de la cellule 
     * @return <code>Hashtable</code> L'objet renvoyé sera la liste des voisins de la cellule
     */
    public Hashtable getVoisins() {
	return this.voisins;
    } // public Hashtable getVoisins()
    
    
    /**
     * Cette méthode sert à ajouter dans la liste des voisins d'une cellule une nouvelle cellue
     * @param <code>String</code> direction ("Nord" ou "Sud" etc ...)
     * @param <code>Cell</code> cellule voisine selon la direction donnée
     */
    public void add(String dir, Cell c) {
	voisins.put(dir,c);
    } // public void add(String dir, Cell c)
    
} // public class Cell implements Constantes