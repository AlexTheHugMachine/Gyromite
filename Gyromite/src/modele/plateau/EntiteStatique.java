package modele.plateau;

/**
 * Ne bouge pas (murs...)
 */
public abstract class EntiteStatique extends Entite {
    public EntiteStatique(Jeu _jeu) {
        super(_jeu);
    }

    @Override
    public boolean peutEtreRamasse(Entite e) {
        return false;
    }
    @Override
    public boolean peutEtreEcrase() { return false; }
    @Override
    public boolean peutServirDeSupport() { return true; }
    @Override
    public boolean peutPermettreDeMonterDescendre() { return false; };
    @Override
    public boolean tuerEntite(Entite e){
        return false;
    }
}

/*
@startuml
+abstract class EntiteStatique extends Entite {
+ EntiteStatique(Jeu _jeu)
+ peutEtreRamasse(Entite e) : boolean
+ peutEtreEcrase() : boolean
+ peutServirDeSupport() : boolean
+ peutPermettreDeMonterDescendre() : boolean
+ tuerEntite(Entite e) : boolean
}
@enduml
 */