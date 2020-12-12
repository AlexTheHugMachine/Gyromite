package modele.plateau;

public class Corde  extends EntiteStatique {
    public Corde (Jeu _jeu) { super (_jeu); }

    public boolean peutPermettreDeMonterDescendre(){ return true; };
    public boolean peutEtreRamasser() { return false;}
}

/*
@startuml
+class Corde  extends EntiteStatique {
+ Corde(Jeu _jeu)
+ peutPermettreDeMonterDescendre() : boolean
+ peutEtreRamasser() : boolean
}
@enduml
 */