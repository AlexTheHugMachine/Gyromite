package modele.plateau;

public class Bombe extends EntiteStatique{

    public Bombe (Jeu _jeu) { super(_jeu); }

    public boolean tuerEntite(Entite e){
        return true;
    }

    public boolean peutEtreEcrase() { return false; }
    public boolean peutServirDeSupport() { return true; }
    public boolean peutPermettreDeMonterDescendre() { return false; };
    public  boolean peutEtreRamasse(Entite e) {return true;}
}
