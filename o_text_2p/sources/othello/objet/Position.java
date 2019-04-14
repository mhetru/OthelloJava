package othello.objet;
import java.util.*;

/**
 * Classe Position
 * représentant une Position
 *
 * @author HETRU Mathieu & MAQUET Frédéric
 * @version 1.0, 03/03/2003
 */
public class Position implements Comparator {
    
    /** attribut x */
    private int x = 0;
    
    /** attribut y */
    private int y = 0;
    
    /** attribut value */
    private int value = 0;
    
    /**
     * constructeur d'objet par défaut
     */
    public Position() {}
    
    /**
     * constructeur d'objets de la classe Position
     */
    public Position(int ligne, int colonne) {
    	
        x = colonne;
        y = ligne;
        
    } // public Position(int ligne, int colonne)
    
    /**
     * constructeur d'objets de la classe Position
     */
    public Position(int ligne, int colonne, int v) {
    	
        x = colonne;
        y = ligne;
        value = v;
        
    } // public Position(int ligne, int colonne)

	/**
	 * permet de définir une abscisse à une position
	 * @param <code>int</code> valeur
	 */
    public void setX(int val_x) {
	x = val_x;
    }
    
    /**
     * Methode qui retourne la coordonnée X de la Cell
     * @return <code>int</code>
     */
    public int getX() {
    	
        return x;
        
    } // public int getX()
    
    /**
	 * permet de définir une ordonnée à une position
	 * @param <code>int</code> valeur
	 */
    public void setY(int val_y) {
	y = val_y;
    }
    
    /**
     * Methode qui retourne la coordonnée y de la Cell
     * @return <code>int</code>
     */
    public int getY() {
    	
        return y;
        
    } // public int getY()
    
    /**
	 * permet de définir une valeur à une position
	 * @param <code>int</code> valeur
	 */
    public void setValue(int v) {
    	value = v;
    }
    
    public int getValue() {
    	return value;
    }
    
    /**
     * Méthode qui retourne la Cell courante sous forme de chaîne de caractères
     * @return <code>String</code>
     */
    public String toString() {
    	
    	String retour = "";
    	
    	int ligne = y + 1;
    	
    	int colonne = x;
    	
    	retour = "" + this.intToCol(colonne) + ligne;
    	
    	return retour;
    } // public String toString()
    
    /**
     * Méthode qui transforme une valeur de colonne java en caractere "humain"
     * @param <code>int</code> valeur de la colonne
     * @return <code>String</code> caractere de la colonne
     */
    private String intToCol(int i) {
    	int compteur = 0;
    	char c = 'a';
    	while(compteur != i) {
	    c++;
	    compteur++;
    	}
    	char tmp[] = { c };
    	return new String(tmp);
    }
    
    /**
     * Méthode1 nécessaire à l'implémentation de l'interface Comparable
     */
    public int compare(Object o1, Object o2) {
    	// ...
    	return 0;
    }
    
    /**
     * Méthode2 nécessaire à l'implémentation de l'interface Comparable
     */
    public boolean equals(Object o) {
    	boolean retour = false;
    	Position p_tmp = null;
    	if (o instanceof Position) {
	    p_tmp = (Position) o;
	    retour = ((this.x == p_tmp.getX()) && (this.y == p_tmp.getY()));
    	} else {
	    throw new ClassCastException();
    	}
    	return retour;
    }
    
} // public class Position
