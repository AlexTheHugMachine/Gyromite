/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import modele.deplacements.Direction;

public abstract class Entite {
    protected Jeu jeu;
    
    public Entite(Jeu _jeu) {
        jeu = _jeu;
    }

    public  void destroy(){

    }

    public abstract  boolean peutEtreRamasse(Entite e);
    public abstract boolean peutEtreEcrase(); // l'entité peut être écrasée (par exemple par une colonne ...)
    public abstract boolean peutServirDeSupport(); // permet de stopper la gravité, prendre appui pour sauter
    public abstract boolean peutPermettreDeMonterDescendre(); // si utilisation de corde (attention, l'environnement ne peut pour l'instant sotker qu'une entité par case (si corde : 2 nécessaires), améliorations à prévoir)
    public abstract boolean tuerEntite(Entite eCible);
    public Entite regarderDansLaDirection(Direction _dir){
        return jeu.regarderDansLaDirection(this, _dir);
    }
}

/*
@startuml
+abstract class Entite{
# jeu : Jeu
--
+ Entite(Jeu _jeu)
+ peutEtreRamasse(Entite e) : abstract boolean
+ peutEtreEcrase() : abstract boolean
+ peutServirDeSupport() : abstract boolean
+ peutPermettreDeMonterDescendre() : abstract boolean
+ tuerEntite(Entite eCible) : abstract boolean
+ regarderDansLaDirection(Direction _dir) : Entite
}
@enduml
 */