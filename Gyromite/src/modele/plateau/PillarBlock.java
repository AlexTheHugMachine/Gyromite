package modele.plateau;

public class PillarBlock  extends EntiteDynamique{

    public PillarBlock(Jeu _jeu){
        super(_jeu);
    }

    public boolean tuerEntite(Entite e){
        if(e instanceof Bot){
            return jeu.killSmick((Bot)e);
        }
        if(e instanceof Heros){
            return jeu.respawn();
        }
        return false;
    }

    public boolean peutEtreEcrase() { return false; }
    public boolean peutServirDeSupport() { return true; }
    public boolean peutPermettreDeMonterDescendre() { return false; };
    public  boolean peutEtreRamasse(Entite e) {return false;}
}

/*
@startuml
+class PillarBlock  extends EntiteDynamique{
+ PillarBlock(Jeu _jeu)
+ tuerEntite(Entite e) : boolean
+ peutEtreEcrase() : boolean
+ peutServirDeSupport() : boolean
+ peutPermettreDeMonterDescendre() : boolean
+ peutEtreRamasse(Entite e) : boolean
}
@enduml
 */