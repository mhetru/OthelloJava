Othello : french game in JAVA
---
What else's fun to learn programmation oriented objects with game?

It was a project at my studies in university at 2003.

Pre-requisite :
JDK (OpenJDK or Oracle JDK)

Text version of the game :

3 versions of Othello game :
- version one player in text mode with keyboard (one player human versus computer with artificial intelligence ; algorithm minmax-alphabeta) ;
- version two players in text mode with keyboard (two players human versus computer with artificial intelligence ; algorithm minmax-alphabeta) ;
- version two players in graphic mode with JAVA SWING packages (two players human or versus computer with artificial intelligence ; algorithm minmax-alphabeta) ;


* version one player in text mode with keyboard (one player human versus computer with artificial intelligence ; algorithm minmax-alphabeta) ; :

- to compile :
```
cd o_text_1p/sources
javac -encoding ISO-8859-15 othello/Othello.java -d ../classes
```

- to play :
```
cd o_text_1p/classes
java othello/Othello
```

- to create javadoc :
```
cd o_text_1p/sources
javadoc -encoding ISO-8859-15 -version -author -package othello othello.interf othello.objet othello.util -d ../docs
```
the javadoc is in o_text_1p/docs folder.

* version two players in text mode with keyboard (two players human versus computer with artificial intelligence ; algorithm minmax-alphabeta) ;

- to compile :
```
cd o_text_2p/sources
javac -encoding ISO-8859-15 othello/Start.java -d ../classes
```

- to play :
```
cd o_text_2p/classes
java othello/Start
```

- to create javadoc :
```
cd o_text_2p/sources
javadoc -encoding ISO-8859-15 -version -author -package othello othello.interf othello.objet othello.util -d ../docs
```
the javadoc is in o_text_2p/docs folder.


* version two players in graphic mode with JAVA SWING packages (two players human or versus computer with artificial intelligence ; algorithm minmax-alphabeta) :

- to compile :
```
cd o_graphic/sources
javac -encoding ISO-8859-15 othello/Othello.java -d ../classes
```

- to play :
```
cd o_graphic/classes
java othello/Othello
```

- to create javadoc :
```
cd o_graphic/sources
javadoc -encoding ISO-8859-15 -version -author -package othello othello.interf othello.objet othhelo.util -d ../docs
```
the javadoc is in o_graphic/docs folder.
