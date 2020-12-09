package modele.plateau;

import modele.deplacements.Direction;

import java.util.Random;

public class Pilliar extends EntiteDynamique {


    public Pilliar(Jeu _jeu) {
        super(_jeu);
    }



    public boolean tuerEntite(Entite e){
        return true;
    }

    public boolean peutEtreEcrase() { return false; }
    public boolean peutServirDeSupport() { return true; }
    public boolean peutPermettreDeMonterDescendre() { return false; };
    public  boolean peutEtreRamasse(Entite e) {return false;}
}