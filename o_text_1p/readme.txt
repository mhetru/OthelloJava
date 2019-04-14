
				OTHELLO  (Mathieu HETRU & Fr�d�ric MAQUET)

 
    Le projet othello est compos� de 8 classes et de 3 interfaces. La classe principale du projet se
nomme Start.java. Les 3 interfaces sont contenus dans le package interf. On y trouve les interfaces 
Constantes.java, Grid.java et Game.java . Les 7 autres classes sont r�parties dans 2 packages (util et 
objet) : Le package util ne contient que la classe Clavier.java qui est n�cessaire lors de la saisie
clavier de l'utilisateur. Le dernier package (objet) contient donc 6 classes (Cell.java, Grille.java, 
Position.java, Partie.java, Ia.java, Jeu.java) Ces classes contiennent toutes les m�thodes n�cessaires
pour le bon d�roulement du jeu. (voir la javadoc)

    Dans le jeu othello, l'utilisateur joue sur un plateau qui est en fait une grille de 8*8. Cette 
grille est compos�e de cellules (Cell.java) qui sont elles-m�mes compos�e d'une position (Position.java)
Lorsque l'utilisateur est invit� � choisir une partie � un ou � deux joueurs, alors selon son choix, 
soit il va cr�er une nouvelle partie (Partie.java) qui sera du type Ia (Ia.java) ou soit du type
du type Jeu (Jeu.java). (voir le code des diff�rentes classes)
 
Pour pouvoir compiler le programme, il faut se placer dans le dossier sources et executer la commande:
	javac -encoding ISO-8859-15 othello/Othello.java -d ../classes
Ainsi tous les bytes codes des classes vont se placer automatiquement dans le dossier classes

Pour pouvoir jouer � othello on doit se placer dans le repertoire classes et taper la commande:
	java othello/Othello
	ou
	java othello.Othello

Pour pouvoir cr�er la javadoc, il faut se placer dans le repertoire sources et taper la commande:
	javadoc -encoding ISO-8859-15 -author -version -package othello othello.interf othello.util othello.objet -d ../docs

























	










