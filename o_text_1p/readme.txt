
				OTHELLO  (Mathieu HETRU & Frédéric MAQUET)

 
    Le projet othello est composé de 8 classes et de 3 interfaces. La classe principale du projet se
nomme Start.java. Les 3 interfaces sont contenus dans le package interf. On y trouve les interfaces 
Constantes.java, Grid.java et Game.java . Les 7 autres classes sont réparties dans 2 packages (util et 
objet) : Le package util ne contient que la classe Clavier.java qui est nécessaire lors de la saisie
clavier de l'utilisateur. Le dernier package (objet) contient donc 6 classes (Cell.java, Grille.java, 
Position.java, Partie.java, Ia.java, Jeu.java) Ces classes contiennent toutes les méthodes nécessaires
pour le bon déroulement du jeu. (voir la javadoc)

    Dans le jeu othello, l'utilisateur joue sur un plateau qui est en fait une grille de 8*8. Cette 
grille est composée de cellules (Cell.java) qui sont elles-mêmes composée d'une position (Position.java)
Lorsque l'utilisateur est invité à choisir une partie à un ou à deux joueurs, alors selon son choix, 
soit il va créer une nouvelle partie (Partie.java) qui sera du type Ia (Ia.java) ou soit du type
du type Jeu (Jeu.java). (voir le code des différentes classes)
 
Pour pouvoir compiler le programme, il faut se placer dans le dossier sources et executer la commande:
	javac -encoding ISO-8859-15 othello/Othello.java -d ../classes
Ainsi tous les bytes codes des classes vont se placer automatiquement dans le dossier classes

Pour pouvoir jouer à othello on doit se placer dans le repertoire classes et taper la commande:
	java othello/Othello
	ou
	java othello.Othello

Pour pouvoir créer la javadoc, il faut se placer dans le repertoire sources et taper la commande:
	javadoc -encoding ISO-8859-15 -author -version -package othello othello.interf othello.util othello.objet -d ../docs

























	










