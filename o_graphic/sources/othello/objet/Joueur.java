package othello.objet;

import othello.interf.*;

/**
 * Classe Joueur "virtuelle"
 * représentant de façon "fictive" un joueur
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
	 * Méthode qui renvoit la valeur du joueur
	 * @return <code>int</code> JOUEUR1 ou JOUEUR2
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Méthode qui renvoit la valeur du type du joueur
	 * @return <code>int</code> HUMAN ou COMPUTER
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * Méthode qui renvoit le nom du joueur
	 * @return <code>String</code> Nom du joueur
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Méthode qui renvoit un booléen
	 * vérifiant si le joueur est un ordinateur
	 */
	public boolean isComputer() {
		return (this.type == COMPUTER);
	}
	
	/**
	 * Méthode qui renvoit un booléen
	 * vérifiant si le joueur est un humain
	 */
	public boolean isHuman() {
		return (this.type == HUMAN);
	}
}