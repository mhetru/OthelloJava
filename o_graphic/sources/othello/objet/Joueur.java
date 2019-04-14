package othello.objet;

import othello.interf.*;

/**
 * Classe Joueur "virtuelle"
 * repr�sentant de fa�on "fictive" un joueur
 */
public class Joueur implements Constantes {
	
	// attributs (cf Constantes.java)
	/** valeur du joueur, JOUEUR1 ou JOUEUR2 */
	private int value;
	/** type du joueur HUMAN ou COMPUTER */
	private int type;
	/** nom du joueur courant */
	private String name;
	
	/**
	 * Constructeur du joueur
	 * @param <code>int</code> JOUEUR1 ou JOUEUR2
	 * @param <code>int</code> HUMAN ou COMPUTER
	 * @param <code>int</code> Nom du joueur
	 */
	public Joueur(int value, int type, String name) {
		this.value = value;
		this.type = type;
		this.name = name;
	}
	
	/**
	 * M�thode qui renvoit la valeur du joueur
	 * @return <code>int</code> JOUEUR1 ou JOUEUR2
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * M�thode qui renvoit la valeur du type du joueur
	 * @return <code>int</code> HUMAN ou COMPUTER
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * M�thode qui renvoit le nom du joueur
	 * @return <code>String</code> Nom du joueur
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * M�thode qui renvoit un bool�en
	 * v�rifiant si le joueur est un ordinateur
	 */
	public boolean isComputer() {
		return (this.type == COMPUTER);
	}
	
	/**
	 * M�thode qui renvoit un bool�en
	 * v�rifiant si le joueur est un humain
	 */
	public boolean isHuman() {
		return (this.type == HUMAN);
	}
}