package othello.objet;

import java.awt.*;
import java.util.*;
import othello.objet.*;
import othello.interf.*;

/**
 * Classe qui repr�sente une cellule qui contient un pion.
 *
 * @author Mathieu HETRU & Fr�d�ric MAQUET
 * @version 1.0,21 Mars 2003
 */
public class Cell implements Constantes {

    /** bool�en qui permet de savoir si une cellule est active ou non */
    private boolean active = false; // cellule du pion (active = true, inactive = false) 
    
    /** objet position de la cellule */
    private Position pos;
    
    /** objet caract�re qui afficher le conteneu de la cellule � l'�cran */
    private char caractere;
    
    /** entier qui permet de distinguer le joueur 1 du joueur 2 */
    private int z=0; // entier qui permet de distinguer le joueur (1=JOUEUR1='x', -1=JOUEUR2='O')
    
    /** attribut couleur qui repr�sente la couleur du pion */
    private Color couleur;
    
    /** Cette Hashtable contient tous les voisins d'une cellule donn�e */
    private Hashtable voisins; // table de hachage pour stocker les 8 voisins de la Cell courante
    
    /**
     * Constructeur de la classe Cell, une cellule est compos� d'une position et d'une liste 
     * de ses voisins
     */
    public Cell(Position p) {
		pos = p;
		this.voisins = new Hashtable();
    } // public Cell(Position p)
    
    
    /**
     * Ce constructeur permet de construire une autre cellule identique � celle pass�e en param�tre
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
     * M�thode qui retourne la colonne de la Cell
     * @return <code>int</code> entier repr�sentant la colonne de la Cell
     */
    public int getX() {
	return pos.getX();
    } // public int getX()
    
    /** 
     * M�thode qui retourne la ligne de la Cell
     * @return <code>int</code> entier repr�sentant la ligne de la Cell
     */
    public int getY() {
		return pos.getY();
    } // public int getY()
    
    /** 
     * M�thode qui retourne si la cellule est active
     * @return <code>boolean</code> boole�n repr�sentant l'�tat de la Cell
     */
    public boolean getEtat() {
		return this.active;
    } // public boolean getEtat()
    
    
    /**
     * M�thode qui rend active la cellule
     */
    private void rendreActive() {
		this.active = true;
    } // private void rendreActive()
    
    
    /** 
     * M�thode qui indique le caract�re de la cellule
     * @return <code>char</code> Le caract�re renvoy� sera X ou O ou .
     */
    public char getChar() {
	return this.caractere;
    } // public char getChar()
    
    /** 
     * M�thode qui renverse la caract�re de la cellule en fonction du joueur pass� en param�tre
     * @param <code> int </code> entier qui repr�sente le joueur  
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
     * Methode qui indique � quel joueur appartient la cellule
     * @return <code>int</code> cet entier indiquera � qui appartient la cellule
     */
    public int quelJoueur() {
	return this.z;
    } // public int QuelJoueur()

    
   
    
    
    /**
     * M�thode qui affiche � l'�cran la cellule
     * @return <code>String</code> Le String renvoy� sera affich� � l'�cran
     */
    public String toString() {
	String retour = CASE_INACTIVE;
	char[] tmp = { caractere }; // on r�cup�re le caract�re de la cellule
	if (active == true) { // si la case est active
	    retour = new String(tmp); // alors on retourne son caractere
	}
	return retour;
    } // public String toString()
    
    
    /**
     * M�thode qui renvoit la couleur du pion de la celulle courante
     * @return <code>Color</code> couleur du pion
     */
    public Color getColor() {
	return this.couleur;
    } // public Color getColor()
    
    
    /**
     * M�thode qui bascule la couleur du pion en couleur adverse
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
     * @return <code>Position</code> L'objet renvoy� sera la position de la cellule
     */
    public Position getPosition() {
		return pos;
    }
    
    /**
     * Methode qui renvoie la liste des voisins de la cellule 
     * @return <code>Hashtable</code> L'objet renvoy� sera la liste des voisins de la cellule
     */
    public Hashtable getVoisins() {
	return this.voisins;
    } // public Hashtable getVoisins()
    
    
    /**
     * Cette m�thode sert � ajouter dans la liste des voisins d'une cellule une nouvelle cellue
     * @param <code>String</code> direction ("Nord" ou "Sud" etc ...)
     * @param <code>Cell</code> cellule voisine selon la direction donn�e
     */
    public void add(String dir, Cell c) {
	voisins.put(dir,c);
    } // public void add(String dir, Cell c)
    
} // public class Cell implements Constantes