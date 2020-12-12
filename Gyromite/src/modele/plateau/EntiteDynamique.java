package modele.plateau;

import modele.deplacements.Direction;

/**
 * Entités amenées à bouger (colonnes, ennemis)
 */
public abstract class EntiteDynamique extends Entite {
    public EntiteDynamique(Jeu _jeu) { super(_jeu); }

    public boolean avancerDirectionChoisie(Direction d) {
        return jeu.deplacerEntite(this, d);
    }
}

/*
@startuml
+abstract class EntiteDynamique extends Entite {
+ EntiteDynamique(Jeu _jeu)
+ avancerDirectionChoisie(Direction d) : boolean
}
@enduml
 */