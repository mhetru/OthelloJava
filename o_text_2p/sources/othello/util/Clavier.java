package othello.util;

import java.io.*;
/**
 * Classe Clavier
 * représentant un clavier virtuel
 * @author Mathieu HETRU & Frédéric MAQUET
 * @version 1.0,21 Mars 2003
 *
 */

public class Clavier {
    /**
     * saisir une String
     * @return <code>chaine de caractère</code> ce qui a été saisi
     */
    public static String readString() {
	
	String tmp = ""; // on définit une String temporaire
	char C = '\0'; // caractère de fin (de tableau de saisi etc ...)
	
	try {
	    while ((C = (char) System.in.read()) !='\n') {
		if (C != '\r')  tmp = tmp+C;
	    }
	} catch (IOException e) {
	    System.out.println("Erreur de frappe");
	    //System.exit(0);
	    tmp = "";
	}
	
	return tmp;
	
    } // public static String readString()
    
    /**
     * saisir un byte
     * @return <code>bye</code> ce qui a été saisi
     */
    public static byte readByte() {
	
	byte x = 0;
	
	try {
	    x = Byte.parseByte(readString());
	} catch (NumberFormatException e) {
	    System.out.println("Format numérique incorrect");
	    System.exit(0);
	}	
	
	return x;
	
    } // public static byte readByte()
    
    /**
     * saisir un short
     * @return <code>short</code> ce qui a été saisi
     */
    public static short readShort() {
	
	short x = 0;
	
	try {
	    x = Short.parseShort(readString());
	} catch (NumberFormatException e) {
	    System.out.println("Format numérique incorrect");
	    System.exit(0);
	}
	
	return x;
	
    } // public static short readShort()
    
    /**
     * saisir un entier
     * @return <code>entier</code> ce qui a été saisi
     */
    public static int readInt() {
	
	int x = 0;
	
	try {
	    x = Integer.parseInt(readString());
	} catch (NumberFormatException e) {
	    System.out.println("Format numérique incorrect");
	    //System.exit(0);
	    x=0;
	}
	
	return x;
	
    } // public static int readInt()
    
    /**
     * saisir un long
     * @return <code>long</code> ce qui a été saisi
     */
    public static long readLong() {
	
	long x = 0;
	
	try {
	    x=Integer.parseInt(readString());
	} catch (NumberFormatException e) {
	    System.out.println("Format numérique incorrect");
	    System.exit(0);
	}	
	
	return x;
	
    } // public static long readLong()
    
    /**
     * saisir un double
     * @return <code>double</code> ce qui a été saisi
     */
    public  static double readDouble() {
	
	double x = 0.0;
	
	try {
	    x = Double.valueOf(readString()).doubleValue();
	} catch (NumberFormatException e) {
	    System.out.println("Format numérique incorrect");
	    System.exit(0);
	}
	
	return x;
	
    } // public  static double readDouble()
    
    /**
     * saisir un float
     * @return <code>float</code> ce qui a été saisi
     */
    public  static float readFloat() {
	
	float x = 0.0f;
	
	try {
	    x = Double.valueOf(readString()).floatValue();
	} catch (NumberFormatException e) {
	    System.out.println("Format numérique incorrect");
	    System.exit(0);
	}
	
	return x;
	
    } // public  static float readFloat()
    
    /**
     * saisir un char
     * @return <code>char</code> ce qui a été saisi
     */
    public  static char readChar() {
	
	String tmp = readString();
	
	if (tmp.length() == 0) {
	    return '\n';
	} else {
	    return tmp.charAt(0);
	}
	
    } // public  static char readChar()
    
} // public class Clavier

