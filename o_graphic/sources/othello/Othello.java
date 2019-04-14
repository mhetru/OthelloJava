package othello;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import othello.interf.*;
import othello.objet.*;
import othello.util.*;

public class Othello extends Jeu implements Constantes {
    
    // attributs de la classe Fenetre
    /** la fenetre de dialogue partie */
    public JDialog fenetre_partie;
    
    /** son container */
    public Container content;
    
    /** le premier panel qui contiendra les images */
    public JPanel panel;
    public JPanel sud;
    
    /** le menu */
    public JMenuBar barre_menu;
    /** les items du menu */
    public JMenuItem item_fichier1;
    public JMenuItem item_fichier2;
    public JCheckBoxMenuItem item_skin1;
    public JCheckBoxMenuItem item_skin2;
    public JCheckBoxMenuItem item_skin3;
    public JCheckBoxMenuItem item_skin4;
    public JCheckBoxMenuItem item_options1;
    public JMenuItem item_aide1;
    public JMenuItem item_aide2;

    /** la grille qui contiendra le dessin "JPanel" => images */
    public Grille grid;
    /** le dessin qui contient les images */
    public Dessin d;
    
    /** chaine récupérée à partir d'un clic de souris sur le plateau */
    public String chaine = "";

    /** le skin par défaut */
    public String skin = "classique";    
    /** images */
    public Icon plateauVide;
    public Icon image1; // pion blanc
    public Icon image2; // pion noir
    public Icon image3; // pion transparent blanc (pour les positions possibles)
    public Icon image4; // pion transparent noir 
    
    /** la chaine de caractere qui sert à recupérer le coup du joueur */
    public String saisie = "";
    
    /** les données nécessaires pour afficher les positions possibles */
    public boolean voir_positions_possibles = true;
    public boolean position_trouve = false;
    public int x_trouve = 0;
    public int y_trouve = 0;
    
    
    // Constructeur de la classe fenetre, avec ses differents attributs
    public Othello() {
	
		// on lance le jeu (par défault);
		super();
		
		// on charge les images avec le skin par défaut
		loadSkin();
		
		// on crée la "grille graphique" ainsi que le dessin
		grid = new Grille();
		
		// on lui affecte une dimension
		f.setSize(320, 410);
		
		// on fixe le container
		content = f.getContentPane();
		
		// on crée le menu
		this.initMenu();

		// JPanel qui contiendra état (status) & infos sur le joueur
		sud = new JPanel(new GridLayout(0,1));
		
		// on crée la barre d'état
		etat = new JLabel(" Partie " + nbparties + " Coup n°" + nbcoups);
		etat.setBackground(Color.white);
		
		infos = new JLabel(" infos sur les joueurs");
		
		// on ajoute les label dans le JPanel "sud"
		sud.add(etat);
		sud.add(infos);
		
		// on ajoute la grille sur le container au centre d'un BorderLayout
		content.add(grid, BorderLayout.CENTER);
		
		// on ajoute la barre d'état à la fenetre
		content.add(sud, BorderLayout.SOUTH);
		
		// le menu est initialisé .. on peut donc l'ajouter à la "fenetre"
		f.setJMenuBar(barre_menu);
		
		// on interdit le redimensionnement de la fenetre (bouton agrandir)
		f.setResizable(false);
		
		// on modifie le comportement de la frame lorsque qu'on demande une fermeture
		f.setDefaultCloseOperation(f.DO_NOTHING_ON_CLOSE);
		
		// on ajoute l'évènement "quitter" à la croix de la fenetre
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			    // on crée une fenetre de dialogue
			    boolean reponse = Msgbox.affQuest(f, "Etes-vous sûr de vouloir quitter ?");
			    // si l'utilisateur a cliqué sur "oui"
			    if (reponse) {
			    	// System.out.println("Have Fun !!");
			    	System.exit(0);
			  	}
			}});
		// on centre la frame
		this.center(f);
		
		// on empaquete tous les objets graphiques dans la fenetre
		f.pack();
		
		// on affiche la fenetre
		f.show();
	
    }// public Fenetre()
    
	/**
	 * Méthode qui charge les images
	 * grace à un skin
	 */
    private void loadSkin() {
		// on fixe les images..
		
		// plateau
		plateauVide = new ImageIcon("othello/skins/" + skin + "/plateau.gif");
		
		// pion blanc
		image1 = new ImageIcon("othello/skins/" + skin + "/case1.gif");
		
		// pion noir
		image2 = new ImageIcon("othello/skins/" + skin + "/case2.gif");	
	
		// pion transparent blanc
		image3 = new ImageIcon("othello/skins/" + skin + "/case3.gif");
		
		// pion transparent noir
		image4 = new ImageIcon("othello/skins/" + skin + "/case4.gif");
    }// private void loadSkin()
    
    /**
     * Méthode center
     * permet d'afficher la fenetre au centre de l'écran
     * avec n'importe quelle résolution ;)
     * @param <code>JFrame</code> Fenetre en parametre
     */
    private void center(JFrame fr) {
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeurEcran = tailleEcran.width;
		int hauteurEcran = tailleEcran.height;
		int largeur = fr.getSize().width;
		int hauteur = fr.getSize().height;
		int xPos = (largeurEcran - largeur) / 2;
		int yPos = (hauteurEcran - hauteur) / 2;
		fr.setLocation(xPos, yPos);
    } // private void center(JFrame fr)
    
    /**
     * Méthode qui permet d'initialiser
     * la barre de Menu sur la Fenetre
     */
    private void initMenu() {
	 	
		// on crée la barre de menu
		barre_menu = new JMenuBar(); 
		
		// on crée le menu "fichier"
		JMenu menu_fichier = new JMenu("Fichier");
		
		// on crée la premiere entrée du menu fichier nommé "Nouvelle Partie"
		item_fichier1 = new JMenuItem("Nouvelle Partie",new ImageIcon("othello/images/nouveau.gif"));
		
		// on met la couleur blanche en couleur de fond au menu "Nouvelle Partie"
		item_fichier1.setBackground(Color.white);
		
		// on ajoute le menu "Nouvelle Partie" au menu "Fichier"
		menu_fichier.add(item_fichier1);
		
		// on ajoute l'évènement de souris sur le bouton "Nouvelle Partie"
		item_fichier1.addActionListener(new Action());
		
		item_fichier2 = new JMenuItem("Quitter",new ImageIcon("othello/images/stop.gif"));
		
		// on met la couleur blanche en couleur de fond au menu "Quitter"
		item_fichier2.setBackground(Color.white);
		
		// on ajoute l'évènement de souris sur le bouton "Quitter"
		item_fichier2.addActionListener(new Action());
		
		// on ajout un séparateur au menu "Fichier" ..
		menu_fichier.addSeparator();
		
		// .. avant d'ajouter le bouton "Quitter" au menu "Fichier"
		menu_fichier.add(item_fichier2);
		
		// on crée le menu skin
		
		JMenu menu_skin = new JMenu("Skin");
		
		// item classique
		item_skin1 = new JCheckBoxMenuItem("Classique");
		item_skin1.setState(true);
		item_skin1.setBackground(Color.white);
		item_skin1.addActionListener(new Action());
		
		// item classique
		item_skin2 = new JCheckBoxMenuItem("Diamond");
		item_skin2.setBackground(Color.white);
		item_skin2.addActionListener(new Action());
		
		// item gold
		item_skin3 = new JCheckBoxMenuItem("Gold");
		item_skin3.setBackground(Color.white);
		item_skin3.addActionListener(new Action());
			
		// item mortvivant
		item_skin4 = new JCheckBoxMenuItem("MortVivant");
		item_skin4.setBackground(Color.white);
		item_skin4.addActionListener(new Action());
		
		// on ajoute ces 4 itemmenus au menu "menu_skin"
		menu_skin.add(item_skin1);
		menu_skin.add(item_skin2);
		menu_skin.add(item_skin3);
		menu_skin.add(item_skin4);
		
		JMenu menu_options = new JMenu("Options");
		item_options1 = new JCheckBoxMenuItem("Voir Possibilités");
		item_options1.setState(true);
		item_options1.setBackground(Color.white);
		item_options1.addActionListener(new Action());
		menu_options.add(item_options1);
		
		/*
		  JMenu menu_config = new JMenu("Configuration");
		  JMenuItem item_config1 = new JMenuItem("Difficulté");
		  item_config1.setBackground(Color.white);
		  JMenuItem item_config2 = new JMenuItem("Changer couleur");
		  item_config2.setBackground(Color.white);
		  menu_config.add(item_config1);
		  menu_config.add(item_config2);
		*/
		
		// on crée le menu "?"
		JMenu menu_aide = new JMenu("?");
		// on crée le sous-menu "Aide"
		item_aide1 = new JMenuItem("Aide",new ImageIcon("othello/images/image.gif"));
		// on met le fond blanc au menu "Aide"
		item_aide1.setBackground(Color.white);
		item_aide1.addActionListener(new Action());
		
		// on crée le sous-menu "A propos de ..."
		item_aide2 = new JMenuItem("A propos de ...",new ImageIcon("othello/images/copyright.gif"));
		// on met le fond blanc au menu "A propos de ..."
		item_aide2.setBackground(Color.white);
		item_aide2.addActionListener(new Action());
		
		// on ajoute le sous-menu "Aide" au menu "?"
		menu_aide.add(item_aide1);
		// on ajoute un séparateur
		menu_aide.addSeparator();
		// on met le sous-menu "A propos de ..." dans le menu "?"
		menu_aide.add(item_aide2);
		
		// on ajoute le menu "Fichier" à la barre de menu
		barre_menu.add(menu_fichier);
		
		// on ajoute le menu "Skin" à la barre de menu
		barre_menu.add(menu_skin);
		
		barre_menu.add(menu_options);
		
		// on ajoute le menu "Config" à la barre de menu
		// monmenu.add(config);
		// on ajoute le menu "?" à la barre de menu
		barre_menu.add(menu_aide);

    }// private void initMenu()

    /**
     * Méthode qui permet d'afficher les images
     */
    public void afficher() {
		d.repaint();
		etat.setText(" Partie [" + nbparties + "/" + nbpartiestotal + "] Coup n°" + nbcoups);
		super.afficheGrille();
    } // public void afficher()

	/**
	 * Algorithme principal
	 * exécuté lors d'un clic de l'utilisateur
	 */    
    public void programme() {
    	
		
		/* if (getMode()==1) {
		    System.out.println("La grille du j1 vaut :" + getGrille().evaluationGrille(getJoueurCourant().getValue()));
		    System.out.println("La grille du j2 vaut :" + getGrille().evaluationGrille(-(getJoueurCourant().getValue())));
		}*/
		
		// on recherche les positions possibles
		rechPositionsPossibles(getGrille().getTour());
		
		// si l'utilisateur peut pas jouer
		if (getGrille().getListePositionsPossibles().size() != 0) {
		    
		    if ( (getJoueurCourant().isComputer()) && (getMode()==1) ) {
			// System.out.println("C'est à l'ordi de JOUER !!!!");
			saisie = faireJouerOrdi();
			/*System.out.println("Appuyez sur [ENTER] pour continuer");
			  while(!Clavier.readString().equals("")) {
			  System.out.println("Appuyez sur [ENTER] pour continuer");
			  }*/
		    } else {
			// System.out.print("Saisissez votre case (q pour quitter):");
			// on saisit la commande
			// saisie = Clavier.readString();
		    }
		    
		    // on injecte notre commande (sous forme de String à notre jeu)
		    commandes(saisie, getGrille().getTour());
		    
		} else {
		    // sinon on alterne le joueur
		    alterneJoueur();
		}
		// on recherche les positions possibles
		rechPositionsPossibles(getGrille().getTour());
		
		
		
		// on affiche le jeu à l'écran
		afficher();
		
		
		
    } // public void programme()

    
    /**
     * Méthode main
     * @param <code>String[]</code> arguments
     */
    public static void main(String[] argv) {
		Othello oth = new Othello();   	
		oth.afficher();
    }

    
    /**
     * Classe Grille
     * permet de contenir un objet "Dessin"
     */
    public class Grille extends JLayeredPane {
	
		/**
		 * Constructeur de la classe Grille
		 */
		public Grille() {
		    
		    // on ne définit pas de layout sur la grille
		    setLayout(null);
		    
		    // on spécifit les dimensions de la grille
		    setPreferredSize(new Dimension(LP,HP));
		    
		    // on crée l'objet "Dessin" qui contiendra les images
		    d = new Dessin();
		    
		    // on ajoute l'évènement clic de la souris sur mon dessin
		    d.addMouseListener(new myMouseClick());
		    
		    // on ajoute l'évènement motion de la souris sur mon dessin
		    d.addMouseMotionListener(new myMouseClick());
		    
		    // on met le "Dessin" dans la Grille au niveau "1" = tout en haut dans la grille
		    add(d, new Integer(1));
		    
		    // on spécifit la taille du Dessin
		    d.setBounds(0, 0, LP, HP);
		    
		} // public Grille()
	
    } // public class Grille extends JLayeredPane
    
    /**
     * Classe Dessin
     * Permet de créer un JPanel qui contiendra les images du jeu
     */
    public class Dessin extends JPanel {
	
		/**
		 * Constructeur de la classe Dessin
		 */
		public Dessin() {
		    
		    // on empêche l'opacité de la fenêtre
		    // setOpaque(false);
		    
		    // on lui spécifit ses dimensions
		    setPreferredSize(new Dimension(LP, HP));
		    
		}// public Dessin()
		
		/**
		 * Méthode commune à java pour les images
		 * Permet de redessiner un objet
		 * en appellant simplement la méthode "repaint()"
		 * sur l'objet courant
		 * ainsi cette méthode sera appellée et redessinnera les images
		 * @param <code>Graphics</code> graphique du jeu
		 */
		public void paintComponent(Graphics g) {
		    
		    // on redessinne d'abord le JPanel
		    super.paintComponent(g);
		    
		    // ensuite on dessine les images
		    
		    // image du plateau
		    plateauVide.paintIcon(null, g, 0, 0);
		    
		    // si le jeu est initialisé ..
		    if (getInit()) {
		    
			    // on parcoure tous les pions
			    for(int y=0;y<SIZE;y++) {
			    	
					for(int x=0;x<SIZE;x++) {
					
						// si les positions possibles doivent etre affichées ..
					    if (voir_positions_possibles) {
					    	
					    	// si une position possible a été trouvé
					    	if (position_trouve) {
					    	
					    		// si le curseur de l'utilisateur se trouve sur une position possible
					    		if ((x == x_trouve) && (y == y_trouve)) {
					    			
					    			// si le joueur courant est le joueur1 (pions blancs)
					    			if (getJoueurCourant().getValue() == JOUEUR1) {
					    				
					    				// on affiche une image transparente blanche
					    				image3.paintIcon(null, g, x_trouve * HC, (7 - Math.abs(y)) * LC);
					    			
					    			// sinon c'est le joueur2 ..
					    			} else if (getJoueurCourant().getValue() == JOUEUR2) {
					    			
					    				image4.paintIcon(null, g, x_trouve * HC, (7 - Math.abs(y)) * LC);
					    			
					    			} // if (getJoueurCourant().getValue() == JOUEUR1)
					    		
					    		} // if ((x == x_trouve) && (y == y_trouve))
					    	
					    	} // if (position_trouve)	
					    
					    } // if (voir_positions_possibles)
					    
					    // si la case à la position y,x appartient au joueur1
					    if (getGrille().getCellAt(new Position(y,x)).quelJoueur() == JOUEUR1) {
						
							// alors on affiche un pion blanc
							image1.paintIcon(null, g, x * HC, (7 - Math.abs(y)) * LC);
						
						// sinon c'est le joueur2
						} else if (getGrille().getCellAt(new Position(y,x)).quelJoueur() == JOUEUR2) {
						
							image2.paintIcon(null, g, x * HC, (7 - Math.abs(y)) * LC);
						
						} // if (getGrille().getCellAt(new Position(y,x)).quelJoueur() == JOUEUR1)		
					
					} // for(int x=0;x<SIZE;x++)
			    
			    } // for(int y=0;y<SIZE;y++)
		    
		    	afficheNbPions();
		    
		  	} // if (getInit())
		    
		    
		    
		}// public void paintComponent(Graphics g)
	
    }// public class Dessin extends JPanel
    
    /**
     * Evènements souris
     * classe interne implémentant les méthodes des classes MouseListener & MouseMotionListener
     */
    public class myMouseClick implements MouseListener, MouseMotionListener {
	
		public void mouseDragged(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		
		// à chaque déplacement du curseur (d'un pixel) cette méthode est exécutée ..
		public void mouseMoved(MouseEvent e) {
			
			// si le jeu a été initialisé ..
			if (getInit()) {
				
				// si le joueur courant n'est pas humain ..
				if (!getJoueurCourant().isHuman()) {
				
					// .. on sort
					return;
				
				} // if (!getJoueurCourant().isHuman())
				
				// si on peut voir les positions possibles ..
				if (voir_positions_possibles) {
					// on récupère les coordonnées du curseur en pixels ..
					int xPosition = e.getX();
					int yPosition = e.getY();
					
					//System.out.println("x = " + xPosition + " y = " + yPosition);
					
					// on transforme ces données en x et y ..
					int y = (7 - Math.abs(yPosition/HC));
					int x = (Math.abs(xPosition/LC));
					
					// on teste si cette position fait partie des positions possibles ..
					if (getGrille().getListePositionsPossibles().contains(new Position(y, x))) {
			
						// .. si oui on positionne une variable témoin booléenne à true
						position_trouve = true;
						// et on sauvegarde les coordonnées de cette position
						x_trouve = x;
						y_trouve = y;
					
					} else {
						
						// .. si non on positionne une variable témoin booléenne à false
						position_trouve = false;
	
					} // if (getGrille().getListePositionsPossibles().contains(new Position(y, x)))
					
					// on réaffiche le dessin
					d.repaint();
				
				} // if (voir_positions_possibles)
			
			} // if (getInit())
		
		} // public void mouseMoved(MouseEvent e)
		
		
		// quand l'utilisateur a cliqué sur la souris ..
		public void mouseClicked(MouseEvent e) {
			
			// on capture ses coordonnées en pixels ..
		    int xPosition = e.getX();
		    int yPosition = e.getY();
		    
		    //System.out.println("------------------------------------------------");
		    
		    //System.out.println("e.getX() = " + xPosition);
		    //System.out.println("e.getX() % 40 = " + (xPosition%40));
		    //System.out.println("e.getX() / 40 = " + xPosition/40);
		    //System.out.println("abs(e.getX() / 40) = " + Math.abs(xPosition/40));
		    
		    //System.out.println("e.getY() = " + yPosition);
		    //System.out.println("e.getY() % 40 = " + (yPosition%40));
		    
		    //System.out.println("e.getY() / 40 = " + yPosition/40);
		    
		    //System.out.println("(7 - abs(e.getY() / 40)) = " + (7 - Math.abs(yPosition/40)));
		    
		    //System.out.println("abs(e.getY() / 40) = " + Math.abs(yPosition/40));
		    
		    System.out.println("------------------------------------------------");
		    
		    // on transforme ces coordonnées en position ..
		    saisie = new Selection().selectionne(xPosition,yPosition);
		    
		    // System.out.println("-->> " + chaine);
		    
		    // on exécute le programme ..
		    programme();
			
			
			
		} // public void mouseClicked(MouseEvent e)

	
    } // public class myMouseClick implements MouseListener, MouseMotionListener
    
    /**
     * Classe gerant les selections
     */
    public class Selection {
		
		// Constructeur vide
		public Selection() {}
		
		// Sélection de case pour Othello
		public String selectionne(int x, int y) {
		    String chain = "";
		    int i,j;
		    
		    // i correspond au numéro de ligne
		    i = (7 - Math.abs(y/HC));
		    
		    // j correspond au numéro de colonne
		    j = Math.abs(x/LC);
		    
		    chain = new Position(i, j).toString();
		    
		    return chain;
		
		} // public String selectionne(int x, int y)
    
    } // public class Selection
    
    /**
     * Classe Action
     * permet de gérer l'évenement du click
     * sur les boutons du menu
     */
    public class Action implements ActionListener {
	
		public Action() {}
		
		public void actionPerformed(ActionEvent e) {
		    
		    // nouvelle partie
		    if (e.getSource() == item_fichier1) {
				
				fenetre_partie = new FenetrePartie(f);
				fenetre_partie.dispose();
				
		    }
		    
		    // skin classique
		    if (e.getSource() == item_skin1) {
				item_skin2.setState(false);
				item_skin3.setState(false);
				item_skin4.setState(false);
				skin = "classique";
				loadSkin();
				d.repaint();
		    }
		    
		    // skin diamond
		    if (e.getSource() == item_skin2) {
				item_skin1.setState(false);
				item_skin3.setState(false);
				item_skin4.setState(false);
				skin = "diamond";
				loadSkin();
				d.repaint();
		    }
		    
		    // skin gold
		    if (e.getSource() == item_skin3) {
				item_skin2.setState(false);
				item_skin1.setState(false);
				item_skin4.setState(false);
				skin = "gold";
				loadSkin();
				d.repaint();
		    }
		    
		    // mortvivant
		    if (e.getSource() == item_skin4) {
				item_skin2.setState(false);
				item_skin3.setState(false);
				item_skin1.setState(false);
				skin = "mortvivant";
				loadSkin();
				d.repaint();
		    }
		    
		    // quitter
		    if (e.getSource() == item_fichier2) {
				boolean reponse = Msgbox.affQuest(f, "Etes-vous sûr de vouloir quitter ?");
			    if (reponse) {
			    	// System.out.println("Have Fun !!");
			    	System.exit(0);
			  	}
		    }
		    
		    // voir possibilités
		    if (e.getSource() == item_options1) {
		    	voir_positions_possibles = !voir_positions_possibles;
		    }
		    
		    // a propos de ..
		    if (e.getSource() == item_aide2) {
		    	Msgbox.affMsg(f, "© 2003 <MAQUET & HETRU>");
		    }
		    
		}// public void actionPerformed(ActionEvent e)
	
    }// public class Action implements ActionListener
    
    /**
     * Classe FenetrePartie
     * représentant la fenetre de dialogue "Nouvelle Partie"
     */  
    public class FenetrePartie extends JDialog {
		private Container contenu;
		private JPanel p1;
		private Scrollbar scrollbar;
		private JLabel tF1;
		private JLabel vide;
		private JPanel p2;
		private JPanel p3;
		private JLabel nom1;
		private JTextField zone1;
		private JTextField zone2;
		private JPanel p4;
		private JLabel nom2;
		private JPanel p5;
		private JLabel choix;
		private Checkbox jeu1;
		private Checkbox jeu2;
		private JPanel p6;
		private JLabel diff;
		private Choice choixdiff;
		private JButton ok;
		
		public FenetrePartie(JFrame fr) {
			
			super(fr, "Nouvelle Partie", true);
		    
		    setSize(350,200);
		    
		    setLocation(300,300);
		    
		    contenu = getContentPane();
		    
		    contenu.setLayout(new BorderLayout());
		    
		    contenu.setBackground(Color.white);
		    
		    //Panel p1 au nord
		    p1 = new JPanel(new GridLayout(0, 2));
		    
		    //p1.setBackground("images/Case2.gif");
		    
		    nbpartiestotal = 1;
		    
		    scrollbar = new Scrollbar(Scrollbar.HORIZONTAL, nbpartiestotal, 1, 1, 11);
		    
		    tF1 = new JLabel("  Nombre de partie(s): " + nbpartiestotal);
		    
		    scrollbar.addAdjustmentListener(new BarreDefilement());
		    
		    vide = new JLabel();
		    
		    p1.add(scrollbar);
		    
		    p1.add(tF1);
		    
		    contenu.add(p1,"North");	
		    
		    //Panel p2 Central 
		    p2 = new JPanel(new GridLayout(0,1));
		    
		    p2.add(new JLabel());
		    
		    p3 = new JPanel(new GridLayout(0,2));
		    
		    nom1 = new JLabel("    Nom du joueur 1:");
		    
		    zone1= new JTextField();
		    
		    p3.add(nom1);
		    
		    p3.add(zone1);
			    
		    p4 = new JPanel(new GridLayout(0,2));
		    
		    nom2 = new JLabel("    Nom du joueur 2:");
		    
		    zone2 = new JTextField();
		    
		    p4.add(nom2);
		    
		    p4.add(zone2);
		    
		    p5 = new JPanel(new GridLayout(0,3));
		    
		    choix = new JLabel("   joueur humain : ");
		    
		    p5.add(choix);
		    
		    jeu1 = new Checkbox("Joueur1");
		    
		    jeu2 = new Checkbox("Joueur2");
		    
		    p5.add(jeu1);
		    p5.add(jeu2);
		    
		    p6 = new JPanel(new GridLayout(0,3));
		    
		    diff = new JLabel("      Difficulté :");
		    p6.add(diff);
		    
		    choixdiff = new Choice();
		    choixdiff.add("   Facile    (Profondeur 1)");
		    choixdiff.add("   Moyen     (Profondeur 3)");
		    choixdiff.add("   Difficile (Profondeur 5)");
		    choixdiff.select(1);
		    p6.add(choixdiff);
		    p2.add(p3);
		    p2.add(p4);
		    p2.add(new JLabel());
		    p2.add(p5);
		    p2.add(new JLabel());
		    p2.add(p6);
		    p2.add(new JLabel());
			    	    
		    contenu.add(p2,"Center");
		    
		    ok = new JButton("OK");
		    ok.addActionListener(new Action_FenetrePartie());
		    contenu.add(ok,"South");
		    
		    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		    setResizable(false);
		    
		    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    	setLocation((d.width - getSize().width)/2,(d.height-getSize().height)/2);
		    
		    pack();
		    show();
		    
		}// public FenetrePartie()
		
		public class BarreDefilement implements AdjustmentListener {
			public void adjustmentValueChanged(AdjustmentEvent evt) {
				// "  Nombre de partie(s): " + nbpartiestotal
				nbpartiestotal = scrollbar.getValue();
				tF1.setText("  Nombre de partie(s): " + nbpartiestotal);
			}	
		}
		
		/**
		 * Action sur les boutons de la fenetre NouvellePartie
		 */
		public class Action_FenetrePartie implements ActionListener {
			
			public Action_FenetrePartie() {}
			
			/**
			 * Méthodes
			 */
		    public void actionPerformed(ActionEvent e) {
			    if (e.getSource()==ok) {
			    	//System.out.println("OK!");
			    	if ((jeu1.getState() == true) && (jeu2.getState() == false)) {
			    		if (zone1.getText().equals("")) {
			    			zone1.setText("Joueur1");
			    		}
			    		joueur1 = new Joueur(JOUEUR1, HUMAN, zone1.getText());
			    		joueur2 = new Joueur(JOUEUR2, COMPUTER, "Computer");
			    		
			    		
			    		initial(1);
			    		
			    		
			    		setVisible(false);
			    		nbcoups = 0;
			    	} else if ((jeu1.getState() == false) && (jeu2.getState() == true)) {
			    		if (zone2.getText().equals("")) {
			    			zone2.setText("Joueur2");
			    		}
			    		
			    		joueur1 = new Joueur(JOUEUR1, COMPUTER, "Computer");
			    		joueur2 = new Joueur(JOUEUR2, HUMAN, zone2.getText());
			    		
			    		
			    		initial(1);
			    		setVisible(false);
			    		nbcoups = 0;
			    	} else {
			    		if ((jeu1.getState() == true) && (jeu2.getState() == true)) {
			    			if (zone1.getText().equals("")) {
			    				zone1.setText("Joueur1");
			    			}
			    			if (zone2.getText().equals("")) {
			    				zone2.setText("Joueur2");
			    			}
			    			joueur1 = new Joueur(JOUEUR1, HUMAN, zone1.getText());
			    			joueur2 = new Joueur(JOUEUR2, HUMAN, zone2.getText());
			    		
			    			
			    			initial(2);
			    			
			    			rechPositionsPossibles(getJoueurCourant().getValue());
			    			setVisible(false);
			    			nbcoups = 0;
			    		} else {
			    			Msgbox.affMsg(f, "Cocher un joueur humain!!");
			    			//System.out.println("Erreur, tu dois saisir et cocher un joueur Humain");
			    		}
			    	}	
			    	
			    	switch(choixdiff.getSelectedIndex()) {
			    		case 0:
			    			// System.out.println("1");
			    			profondeur = 1;
			    			break;
			    		case 1:
			    			// System.out.println("3");
			    			profondeur = 3;
			    			break;
			    		case 2:
			    			// System.out.println("5");
			    			profondeur = 5;
			    			break;
			    	}
			    	
			    }
			    programme();
			}
		}
	
    }// public class FenetrePartie()
    
    
}
